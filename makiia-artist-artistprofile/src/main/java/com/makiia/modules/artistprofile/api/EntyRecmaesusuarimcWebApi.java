package com.makiia.modules.artistprofile.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makiia.crosscutting.domain.constants.ApiConstants;
import com.makiia.crosscutting.domain.constants.Constants;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaDto;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaResponse;
import com.makiia.crosscutting.domain.model.EntyArtistUtiliDto;
import com.makiia.crosscutting.domain.model.EntyDeleteDto;
import com.makiia.crosscutting.exceptions.MicroEventException;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.modules.artistprofile.usecase.EntyApjmaeshomepamaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/artistprofile", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EntyRecmaesusuarimcWebApi {
    @Autowired
    private EntyApjmaeshomepamaService service;
    @GetMapping("getall")
    @ApiOperation(httpMethod = ApiConstants.GET_HTTP, value = ApiConstants.GET_DESC, notes = "")
    public ResponseEntity<EntyApjmaeshomepamaResponse> getAll()
            throws EBusinessException, MicroEventException {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping
    @ApiOperation(httpMethod = ApiConstants.GET_HTTP, value = ApiConstants.GET_ALL_DESC, notes = "")
    public ResponseEntity<EntyApjmaeshomepamaResponse> getAll(@RequestParam(value = "currentpage",required = false,defaultValue = "0") int currentPage,
                                                                @RequestParam(value = "pagesize",required = false,defaultValue = "10")  int pagesize,
                                                                @RequestParam(value = "parameter",required = false) String parameter,
                                                                @RequestParam(value = "filter",required = false ) String filter)
     throws EBusinessException, MicroEventException {
          return new ResponseEntity<>(service.getAll(currentPage, pagesize, parameter ,filter), HttpStatus.OK);
    }
    @GetMapping(Constants.ID_PRICES_PARAM)
    @ApiOperation(httpMethod = ApiConstants.GET_HTTP, value = ApiConstants.GET_DESC, notes = "")
    public ResponseEntity<EntyApjmaeshomepamaDto>get(@PathVariable(Constants.ID_REST) Integer id)
            throws EBusinessException, MicroEventException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PostMapping("create")
    @ApiOperation(httpMethod = ApiConstants.POST_HTTP, value = ApiConstants.POST_DESC, notes = "")
    public ResponseEntity<EntyApjmaeshomepamaResponse> create(@RequestBody EntyApjmaeshomepamaResponse dto)
            throws EBusinessException, MicroEventException {
        return new ResponseEntity<>(service.saveBefore(dto), HttpStatus.CREATED);
    }

    @PutMapping("update")
    @ApiOperation(httpMethod = ApiConstants.PUT_HTTP, value = ApiConstants.PUT_DESC, notes = "")
    public ResponseEntity<EntyApjmaeshomepamaResponse> update(@RequestBody EntyApjmaeshomepamaResponse dto)
            throws EBusinessException, MicroEventException {
        return new ResponseEntity<>(service.updateAll(dto), HttpStatus.CREATED);
    }

    @PatchMapping("changestatus")
    @ApiOperation(httpMethod = ApiConstants.PATCH_HTTP, value = ApiConstants.PATCH_DESC, notes = "")
    public String changestatus(@RequestBody List<EntyArtistUtiliDto> dto) throws EBusinessException, MicroEventException {
        return service.changestatusAll(dto);
    }

    @DeleteMapping("delete")
    @ApiOperation(httpMethod = ApiConstants.DELETE_HTTP, value = ApiConstants.DELETE_DESC, notes = "")
    public String delete(@RequestBody List<EntyDeleteDto> dto) throws EBusinessException, MicroEventException {
        return service.deleteAll(dto);
    }

}