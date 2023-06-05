package com.makiia.modules.bus.contracts;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaResponse;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import java.util.List;

public interface  IjpaDataProviders<T> {

    //List<T> getAll() throws EBusinessException;
    EntyApjmaeshomepamaResponse getAll() throws  EBusinessException;
    EntyApjmaeshomepamaResponse getAll (int currentPage , int pageSize, int parameter, String filter) throws EBusinessException;
    T get(Integer id) throws EBusinessException;
    T save(T dto) throws EBusinessException;
    List<T> save(List<T> dto) throws EBusinessException;
    T update(Integer id, T dto) throws EBusinessException;
    void delete(Integer id) throws EBusinessException;
}
