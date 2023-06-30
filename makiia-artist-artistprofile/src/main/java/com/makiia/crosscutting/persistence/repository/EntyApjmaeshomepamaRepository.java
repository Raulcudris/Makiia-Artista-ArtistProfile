package com.makiia.crosscutting.persistence.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.makiia.crosscutting.persistence.entity.EntyApjmaeshomepama;

public interface EntyApjmaeshomepamaRepository extends JpaRepository<EntyApjmaeshomepama,Integer>
{
        String FILTER_ARTISTPROFILE_RECUNIKEYREGl_QUERY = "select c from EntyApjmaeshomepama c  where c.recUnikeyAphp  = ?1 and c.apjEstregAphp ='1'";
        @Query(value = FILTER_ARTISTPROFILE_RECUNIKEYREGl_QUERY)
        Page<EntyApjmaeshomepama> findByRecUnikeyAphp(Integer filter, Pageable pageable);
        
        String FILTER_ARTISTPROFILE_APJNROREGAPHP_QUERY = "select c from EntyApjmaeshomepama c where UPPER(c.apjNroregAphp)"+
                                                   " like concat('%',upper(?1),'%') and c.apjEstregAphp ='1' ";
        @Query(value =FILTER_ARTISTPROFILE_APJNROREGAPHP_QUERY)
        Page<EntyApjmaeshomepama> findByApjNroregAphp(String filter, Pageable pageable);
}
