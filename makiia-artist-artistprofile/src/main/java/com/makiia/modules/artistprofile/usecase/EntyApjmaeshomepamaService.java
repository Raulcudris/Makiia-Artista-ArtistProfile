package com.makiia.modules.artistprofile.usecase;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaDto;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaResponse;
import com.makiia.crosscutting.domain.model.EntyArtistUtiliDto;
import com.makiia.crosscutting.domain.model.EntyDeleteDto;
import com.makiia.crosscutting.exceptions.ExceptionBuilder;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.messages.SearchMessages;
import com.makiia.modules.artistprofile.dataproviders.jpa.JpaEntyApjmaeshomepamaDataProviders;
import com.makiia.modules.artistprofile.services.UseCase;
import com.makiia.modules.artistprofile.services.UsecaseServices;

@UseCase
public class EntyApjmaeshomepamaService extends UsecaseServices<EntyApjmaeshomepamaDto, JpaEntyApjmaeshomepamaDataProviders>
{
    @Autowired
    private JpaEntyApjmaeshomepamaDataProviders jpaDataProviders;
    @PostConstruct
    public void init(){
        this.ijpaDataProvider = jpaDataProviders;
    }


    public EntyApjmaeshomepamaResponse saveBefore(EntyApjmaeshomepamaResponse dto) throws EBusinessException {
        try{
            List<EntyApjmaeshomepamaDto>  dtoAux = this.ijpaDataProvider.save(dto.getRspData());
            for (EntyApjmaeshomepamaDto dtox : dtoAux){
                dtox.setApjEstregAphp("1");
            }
        
            dtoAux = this.ijpaDataProvider.save(dtoAux);
            dto.setRspValue("OK");
            dto.setRspMessage("OK");
            dto.setRspParentKey("NA");
            dto.setRspAppKey("NA");
            dto.setRspData(dtoAux);
            return dto;
        }catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    public EntyApjmaeshomepamaResponse updateAll(EntyApjmaeshomepamaResponse dto) throws EBusinessException {
        try {
            List<EntyApjmaeshomepamaDto> dtoAux = dto.getRspData();

            for (EntyApjmaeshomepamaDto dtox : dtoAux){
                dtox = this.ijpaDataProvider.update(dtox.getRecUnikeyAphp(),dtox);
            }
            dto.setRspValue("OK");
            dto.setRspMessage("OK");
            dto.setRspParentKey("NA");
            dto.setRspAppKey("NA");
            dto.setRspData(dtoAux);
            return dto;

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    public String changestatusAll(List<EntyArtistUtiliDto> dto) throws EBusinessException {
        try {
            EntyApjmaeshomepamaDto rspto = new EntyApjmaeshomepamaDto();

            for (EntyArtistUtiliDto dtox : dto) {
                rspto = this.ijpaDataProvider.get(dtox.getRecPKey());
                rspto.setApjEstregAphp(dtox.getRecEstreg()); 
                this.ijpaDataProvider.update(dtox.getRecPKey(),rspto);
            }
            return "OK";

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    public String deleteAll(List<EntyDeleteDto> dto) throws EBusinessException {
        try {

            for (EntyDeleteDto dtox : dto) {
                this.ijpaDataProvider.delete(dtox.getRecPKey());
            }
            return "OK";

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.DELETE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.DELETE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }



   

}
