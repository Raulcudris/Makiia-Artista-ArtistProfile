package com.makiia.crosscutting.persistence.repository;
import com.makiia.crosscutting.persistence.entity.EntyApjmaeshomepama;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EntyApjmaeshomepamaRepository extends JpaRepository<EntyApjmaeshomepama,Integer>
{
        String FILTER_USUARIO_RECUNIKEYREGl_QUERY = "select c from EntyApjmaeshomepama c  where c.recUnikeyAphp  = ?1 and c.apjEstregAphp ='1'";
        @Query(value = FILTER_USUARIO_RECUNIKEYREGl_QUERY)
        Page<EntyApjmaeshomepama> findByRecUnikeyAphp(Integer parameter, Pageable pageable);
        
        String FILTER_USUARIO_RECNROREGREUS_QUERY = "select c from EntyApjmaeshomepama c where UPPER(c.apjNroregAphp)"+
                                                   " like concat('%',upper(?1),'%') and c.apjEstregAphp ='1' ";
        @Query(value =FILTER_USUARIO_RECNROREGREUS_QUERY)
        Page<EntyApjmaeshomepama> findByApjNroregAphp(String filter, Pageable pageable);

}
