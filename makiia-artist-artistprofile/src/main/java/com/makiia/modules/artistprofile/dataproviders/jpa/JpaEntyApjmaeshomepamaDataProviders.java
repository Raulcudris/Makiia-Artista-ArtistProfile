package com.makiia.modules.artistprofile.dataproviders.jpa;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaDto;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaResponse;
import com.makiia.crosscutting.domain.model.PaginationResponse;
import com.makiia.crosscutting.exceptions.DataProvider;
import com.makiia.crosscutting.exceptions.ExceptionBuilder;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.messages.SearchMessages;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyApjmaeshomepama;
import com.makiia.crosscutting.persistence.repository.EntyApjmaeshomepamaRepository;
import com.makiia.modules.artistprofile.dataproviders.IjpaEntyApjmaeshomepamaDataProviders;

@DataProvider
public class JpaEntyApjmaeshomepamaDataProviders implements IjpaEntyApjmaeshomepamaDataProviders {

    @Autowired
    private EntyApjmaeshomepamaRepository repository;
    @Autowired
    @Qualifier("entyApjmaeshomepamaSaveResponseTranslate")
    private Translator<EntyApjmaeshomepama, EntyApjmaeshomepamaDto> saveResponseTranslate;
    @Autowired
    @Qualifier("entyApjmaeshomepamaDtoToEntityTranslate")
    private Translator<EntyApjmaeshomepamaDto, EntyApjmaeshomepama> dtoToEntityTranslate;

    @Override
    public EntyApjmaeshomepamaResponse getAll() throws EBusinessException {
        try {
            List<EntyApjmaeshomepama> responses = (List<EntyApjmaeshomepama>) repository.findAll();
            int currentPage = 0;
            int totalPageSize = responses.size();
            Pageable pageable = PageRequest.of(currentPage, totalPageSize);
            // Pageable paginacion
            Page<EntyApjmaeshomepama> ResponsePage = null;
            ResponsePage = repository.findAll(pageable);

            List<EntyApjmaeshomepama> ListPage = ResponsePage.getContent();
            List<EntyApjmaeshomepamaDto> content  = ListPage.stream().map(p ->mapToDto(p)).collect(Collectors.toList());

            EntyApjmaeshomepamaResponse response = new EntyApjmaeshomepamaResponse();
            response.setRspMessage(response.getRspMessage());
            response.setRspValue(response.getRspValue());

            currentPage = currentPage + 1;
            String nextPageUrl = "LocalHost";
            String previousPageUrl = "LocalHost";
            response.setRspPagination(headResponse(currentPage, totalPageSize, content.size(),
                    ResponsePage.getTotalPages(), ResponsePage.hasNext(), ResponsePage.hasPrevious(), nextPageUrl,
                    previousPageUrl));
            response.setRspData(content);
            return response;

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyApjmaeshomepamaResponse getAll(int currentPage, int totalPageSize, int parameter, String filter)
            throws EBusinessException {
        try {
            currentPage = currentPage - 1;
            Pageable pageable = PageRequest.of(currentPage, totalPageSize);
            Page<EntyApjmaeshomepama> ResponsePage = null;
            
            if (parameter == 0) {
                ResponsePage = repository.findByApjNroregAphp(filter, pageable);
            } else {
               ResponsePage = repository.findByRecUnikeyAphp(parameter, pageable);
            }

            List<EntyApjmaeshomepama> ListPage = ResponsePage.getContent();
            List<EntyApjmaeshomepamaDto> content = ListPage.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

            EntyApjmaeshomepamaResponse response = new EntyApjmaeshomepamaResponse();
            response.setRspMessage(response.getRspMessage());
            response.setRspValue(response.getRspValue());

            currentPage = currentPage + 1;
            String nextPageUrl = "LocalHost";
            String previousPageUrl = "LocalHost";
            response.setRspPagination(headResponse(currentPage, totalPageSize, content.size(),
                    ResponsePage.getTotalPages(), ResponsePage.hasNext(), ResponsePage.hasPrevious(), nextPageUrl,
                    previousPageUrl));
            response.setRspData(content);
            return response;

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyApjmaeshomepamaDto get(Integer id) throws EBusinessException {
        try {
            return saveResponseTranslate.translate(repository.findById(id).get());
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyApjmaeshomepamaDto save(EntyApjmaeshomepamaDto dto) throws EBusinessException {
        try {
            return saveResponseTranslate.translate(repository.save(dtoToEntityTranslate.translate(dto)));
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public List<EntyApjmaeshomepamaDto> save(List<EntyApjmaeshomepamaDto> dtos) throws EBusinessException {
        try {
            List<EntyApjmaeshomepama> entities = new ArrayList<>();

            for (EntyApjmaeshomepamaDto dto : dtos) {
                entities.add(dtoToEntityTranslate.translate(dto));
            }
            dtos = new ArrayList<>();
            for (EntyApjmaeshomepama entity : repository.saveAll(entities)) {
                dtos.add(saveResponseTranslate.translate(entity));
            }
            return dtos;
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyApjmaeshomepamaDto update(Integer id, EntyApjmaeshomepamaDto dto) throws EBusinessException {
        try {
            EntyApjmaeshomepama entity = dtoToEntityTranslate.translate(dto);
            EntyApjmaeshomepama old = repository.findById(id).get();

            old.setRecUnikeyAphp(
                    Objects.nonNull(dto.getRecUnikeyAphp()) && !entity.getRecUnikeyAphp().equals(0)
                            ? entity.getRecUnikeyAphp()
                            : old.getRecUnikeyAphp());

            old.setApjNroregAphp(
                    Objects.nonNull(dto.getApjNroregAphp()) && !entity.getApjNroregAphp().isEmpty()
                            ? entity.getApjNroregAphp()
                            : old.getApjNroregAphp());

            old.setApjTitgruAphp(
                    Objects.nonNull(dto.getApjTitgruAphp()) && !entity.getApjTitgruAphp().isEmpty()
                            ? entity.getApjTitgruAphp()
                            : old.getApjTitgruAphp());

            old.setApjLnkgruAphp(
                    Objects.nonNull(dto.getApjLnkgruAphp()) && !entity.getApjLnkgruAphp().isEmpty()
                            ? entity.getApjLnkgruAphp()
                            : old.getApjLnkgruAphp());

            old.setApjExlemaAphp(
                    Objects.nonNull(dto.getApjExlemaAphp()) && !entity.getApjExlemaAphp().isEmpty()
                            ? entity.getApjExlemaAphp()
                            : old.getApjExlemaAphp());

            old.setApjTipartAphp(
                    Objects.nonNull(dto.getApjTipartAphp()) && !entity.getApjTipartAphp().isEmpty()
                            ? entity.getApjTipartAphp()
                            : old.getApjTipartAphp());

            old.setRecNroregReus(
                    Objects.nonNull(dto.getRecNroregReus()) && !entity.getRecNroregReus().isEmpty()
                            ? entity.getRecNroregReus()
                            : old.getRecNroregReus());

            old.setSisCodpaiSipa(
                    Objects.nonNull(dto.getSisCodpaiSipa()) && !entity.getSisCodpaiSipa().isEmpty()
                            ? entity.getSisCodpaiSipa()
                            : old.getSisCodpaiSipa());

            old.setSisCodproSipr(
                    Objects.nonNull(dto.getSisCodproSipr()) && !entity.getSisCodproSipr().isEmpty()
                            ? entity.getSisCodproSipr()
                            : old.getSisCodproSipr());

            old.setSisIdedptSidp(
                    Objects.nonNull(dto.getSisIdedptSidp()) && !entity.getSisIdedptSidp().isEmpty()
                            ? entity.getSisIdedptSidp()
                            : old.getSisIdedptSidp());

            old.setApjGeoestAphp(
                    Objects.nonNull(dto.getApjGeoestAphp()) && !entity.getApjGeoestAphp().isEmpty()
                            ? entity.getApjGeoestAphp()
                            : old.getApjGeoestAphp());

            old.setApjGeolatAphp(
                    Objects.nonNull(dto.getApjGeolatAphp()) && !entity.getApjGeolatAphp().equals(0)
                            ? entity.getApjGeolatAphp()
                            : old.getApjGeolatAphp());

            old.setApjGeolonAphp(
                    Objects.nonNull(dto.getApjGeolonAphp()) && !entity.getApjGeolonAphp().equals(0)
                            ? entity.getApjGeolonAphp()
                            : old.getApjGeolonAphp());

            old.setApjValtraAphp(
                    Objects.nonNull(dto.getApjValtraAphp()) && !entity.getApjValtraAphp().equals(0)
                            ? entity.getApjValtraAphp()
                            : old.getApjValtraAphp());

            old.setApjValcalAphp(
                    Objects.nonNull(dto.getApjValcalAphp()) && !entity.getApjValcalAphp().equals(0)
                            ? entity.getApjValcalAphp()
                            : old.getApjValcalAphp());

            old.setApjValpunAphp(
                    Objects.nonNull(dto.getApjValpunAphp()) && !entity.getApjValpunAphp().equals(0)
                            ? entity.getApjValpunAphp()
                            : old.getApjValpunAphp());

            old.setApjValpopAphp(
                    Objects.nonNull(dto.getApjValpopAphp()) && !entity.getApjValpopAphp().equals(0)
                            ? entity.getApjValpopAphp()
                            : old.getApjValpopAphp());

            old.setApjTotsegAphp(
                    Objects.nonNull(dto.getApjTotsegAphp()) && !entity.getApjTotsegAphp().equals(0)
                            ? entity.getApjTotsegAphp()
                            : old.getApjTotsegAphp());

            old.setApjEstautAphp(
                    Objects.nonNull(dto.getApjEstautAphp()) && !entity.getApjEstautAphp().isEmpty()
                            ? entity.getApjEstautAphp()
                            : old.getApjEstautAphp());

            old.setApjEstgesAphp(
                    Objects.nonNull(dto.getApjEstgesAphp()) && !entity.getApjEstgesAphp().isEmpty()
                            ? entity.getApjEstgesAphp()
                            : old.getApjEstgesAphp());

            old.setApjEstdisAphp(
                    Objects.nonNull(dto.getApjEstdisAphp()) && !entity.getApjEstdisAphp().isEmpty()
                            ? entity.getApjEstdisAphp()
                            : old.getApjEstdisAphp());
			
			old.setApjEstlocAphp(
                    Objects.nonNull(dto.getApjEstlocAphp()) && !entity.getApjEstlocAphp().isEmpty()
                            ? entity.getApjEstlocAphp()
                            : old.getApjEstlocAphp());

            old.setApjEstpenAphp(
                    Objects.nonNull(dto.getApjEstpenAphp()) && !entity.getApjEstpenAphp().isEmpty()
                            ? entity.getApjEstpenAphp()
                            : old.getApjEstpenAphp());

            old.setApjPeifecAphp(
                    Objects.nonNull(dto.getApjPeifecAphp()) && !entity.getApjPeifecAphp().equals(0)
                            ? entity.getApjPeifecAphp()
                            : old.getApjPeifecAphp());

            old.setApjPeihorAphp(
                    Objects.nonNull(dto.getApjPeihorAphp()) && !entity.getApjPeihorAphp().equals(0)
                            ? entity.getApjPeihorAphp()
                            : old.getApjPeihorAphp());

            old.setApjPeikeyAphp(
                    Objects.nonNull(dto.getApjPeikeyAphp()) && !entity.getApjPeikeyAphp().equals(0)
                            ? entity.getApjPeikeyAphp()
                            : old.getApjPeikeyAphp());

            old.setApjPeffecAphp(
                    Objects.nonNull(dto.getApjPeffecAphp()) && !entity.getApjPeffecAphp().equals(0)
                            ? entity.getApjPeffecAphp()
                            : old.getApjPeffecAphp());

            old.setApjPefhorAphp(
                    Objects.nonNull(dto.getApjPefhorAphp()) && !entity.getApjPefhorAphp().equals(0)
                            ? entity.getApjPefhorAphp()
                            : old.getApjPefhorAphp());

            old.setApjPefkeyAphp(
                    Objects.nonNull(dto.getApjPefkeyAphp()) && !entity.getApjPefkeyAphp().equals(0)
                            ? entity.getApjPefkeyAphp()
                            : old.getApjPefkeyAphp());

            old.setApjLegalrAphp(
                    Objects.nonNull(dto.getApjLegalrAphp()) && !entity.getApjLegalrAphp().equals(0)
                            ? entity.getApjLegalrAphp()
                            : old.getApjLegalrAphp());

            old.setApjKeyestAphp(
                    Objects.nonNull(dto.getApjKeyestAphp()) && !entity.getApjKeyestAphp().isEmpty()
                            ? entity.getApjKeyestAphp()
                            : old.getApjKeyestAphp());

            old.setApjKeylocAphp(
                    Objects.nonNull(dto.getApjKeylocAphp()) && !entity.getApjKeylocAphp().isEmpty()
                            ? entity.getApjKeylocAphp()
                            : old.getApjKeylocAphp());

            old.setApjKeyageAphp(
                    Objects.nonNull(dto.getApjKeyageAphp()) && !entity.getApjKeyageAphp().isEmpty()
                            ? entity.getApjKeyageAphp()
                            : old.getApjKeyageAphp());

            old.setApjKeyraiAphp(
                    Objects.nonNull(dto.getApjKeyraiAphp()) && !entity.getApjKeyraiAphp().equals(0)
                            ? entity.getApjKeyraiAphp()
                            : old.getApjKeyraiAphp());

            old.setApjKeyrafAphp(
                    Objects.nonNull(dto.getApjKeyrafAphp()) && !entity.getApjKeyrafAphp().equals(0)
                            ? entity.getApjKeyrafAphp()
                            : old.getApjKeyrafAphp());

            old.setSisCountaRkey(
                    Objects.nonNull(dto.getSisCountaRkey()) && !entity.getSisCountaRkey().equals(0)
                            ? entity.getSisCountaRkey()
                            : old.getSisCountaRkey());

            old.setSisCountbRkey(
                    Objects.nonNull(dto.getSisCountbRkey()) && !entity.getSisCountbRkey().equals(0)
                            ? entity.getSisCountbRkey()
                            : old.getSisCountbRkey());

            old.setSisCountcRkey(
                    Objects.nonNull(dto.getSisCountcRkey()) && !entity.getSisCountcRkey().equals(0)
                            ? entity.getSisCountcRkey()
                            : old.getSisCountcRkey());

            old.setSisCountdRkey(
                    Objects.nonNull(dto.getSisCountdRkey()) && !entity.getSisCountdRkey().equals(0)
                            ? entity.getSisCountdRkey()
                            : old.getSisCountdRkey());

            old.setSisCounteRkey(
                    Objects.nonNull(dto.getSisCounteRkey()) && !entity.getSisCounteRkey().equals(0)
                            ? entity.getSisCounteRkey()
                            : old.getSisCounteRkey());

            old.setApjEstregAphp(
                    Objects.nonNull(dto.getApjEstregAphp()) && !entity.getApjEstregAphp().isEmpty()
                            ? entity.getApjEstregAphp()
                            : old.getApjEstregAphp());


            return saveResponseTranslate.translate(repository.save(old));
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();

        }
    }

    @Override
    public void delete(Integer id) throws EBusinessException {
        try {
            repository.delete(repository.findById(id).get());
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.DELETE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.DELETE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    private EntyApjmaeshomepamaDto mapToDto(EntyApjmaeshomepama p) {
        EntyApjmaeshomepamaDto dto = new EntyApjmaeshomepamaDto();

        dto.setRecUnikeyAphp(p.getRecUnikeyAphp());
        dto.setApjNroregAphp(p.getApjNroregAphp());
        dto.setApjTitgruAphp(p.getApjTitgruAphp());
        dto.setApjLnkgruAphp(p.getApjLnkgruAphp());
        dto.setApjExlemaAphp(p.getApjExlemaAphp());
        dto.setApjTipartAphp(p.getApjTipartAphp());
        dto.setRecNroregReus(p.getRecNroregReus());
        dto.setSisCodpaiSipa(p.getSisCodpaiSipa());
        dto.setSisCodproSipr(p.getSisCodproSipr());
        dto.setSisIdedptSidp(p.getSisIdedptSidp());
        dto.setApjGeoestAphp(p.getApjGeoestAphp());
        dto.setApjGeolatAphp(p.getApjGeolatAphp());
        dto.setApjGeolonAphp(p.getApjGeolonAphp());
        dto.setApjValtraAphp(p.getApjValtraAphp());
        dto.setApjValcalAphp(p.getApjValcalAphp());
        dto.setApjValpunAphp(p.getApjValpunAphp());
        dto.setApjValpopAphp(p.getApjValpopAphp());
        dto.setApjTotsegAphp(p.getApjTotsegAphp());
        dto.setApjEstautAphp(p.getApjEstautAphp());
        dto.setApjEstgesAphp(p.getApjEstgesAphp());
        dto.setApjEstdisAphp(p.getApjEstdisAphp());
        dto.setApjEstlocAphp(p.getApjEstlocAphp());
        dto.setApjEstpenAphp(p.getApjEstpenAphp());
        dto.setApjPeifecAphp(p.getApjPeifecAphp());
        dto.setApjPeihorAphp(p.getApjPeihorAphp());
        dto.setApjPeikeyAphp(p.getApjPeikeyAphp());
        dto.setApjPeffecAphp(p.getApjPeffecAphp());
        dto.setApjPefhorAphp(p.getApjPefhorAphp());
        dto.setApjPefkeyAphp(p.getApjPefkeyAphp());
        dto.setApjLegalrAphp(p.getApjLegalrAphp());
        dto.setApjKeyestAphp(p.getApjKeyestAphp());
        dto.setApjKeylocAphp(p.getApjKeylocAphp());
        dto.setApjKeyageAphp(p.getApjKeyageAphp());
        dto.setApjKeyraiAphp(p.getApjKeyraiAphp());
        dto.setApjKeyrafAphp(p.getApjKeyrafAphp());
        dto.setSisCountaRkey(p.getSisCountaRkey());
        dto.setSisCountbRkey(p.getSisCountbRkey());
        dto.setSisCountcRkey(p.getSisCountcRkey());
        dto.setSisCountdRkey(p.getSisCountdRkey());
        dto.setSisCounteRkey(p.getSisCounteRkey());
        dto.setApjEstregAphp(p.getApjEstregAphp());
        return dto;
    }

    public static PaginationResponse headResponse(int currentPage, int totalPageSize,
            long totalResults, int totalPages,
            boolean hasNextPage, boolean hasPreviousPage,
            String nextpageUrl, String previousPageUrl) {
        return PaginationResponse.builder()
                .currentPage(currentPage)
                .totalPageSize(totalPageSize)
                .totalResults(totalResults)
                .totalPages(totalPages)
                .hasNextPage(hasNextPage)
                .hasPreviousPage(hasPreviousPage)
                .nextPageUrl(nextpageUrl)
                .previousPageUrl(previousPageUrl)
                .build();

    }
}
