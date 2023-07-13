package com.makiia.crosscutting.domain.model;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EntyApjmaeshomepamaDto {
    private Integer apjIdeunikeyAphp;
    private String  apjNroregAphp;
    private String  apjTitgruAphp;
    private String  apjLnkgruAphp;
    private String  apjExlemaAphp;
    private String  apjTipartAphp;
    private String  recNroregReus;
    private String  sisCodpaiSipa;
    private String  sisCodproSipr;
    private String  sisIdedptSidp;
    private String  apjGeoestAphp;
    private Float   apjGeolatAphp;
    private Float   apjGeolonAphp;
    private Double  apjValtraAphp;
    private Double  apjValcalAphp;
    private Double  apjValpunAphp;
    private Double  apjValpopAphp;
    private Integer apjTotsegAphp;
    private String  apjEstautAphp;
    private String  apjEstgesAphp;
    private String  apjEstdisAphp;
    private String  apjEstlocAphp;
    private String  apjEstpenAphp;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  apjPeifecAphp;
    private Float   apjPeihorAphp;
    private Integer apjPeikeyAphp;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  apjPeffecAphp;
    private Float   apjPefhorAphp;
    private Integer apjPefkeyAphp;
    private Integer apjLegalrAphp;
    private String  apjKeyestAphp;
    private String  apjKeylocAphp;
    private String  apjKeyageAphp;
    private Integer apjKeyraiAphp;
    private Integer apjKeyrafAphp;
    private Integer sisCountaRkey;
    private Integer sisCountbRkey;
    private Integer sisCountcRkey;
    private Integer sisCountdRkey;
    private Integer sisCounteRkey;
    private String  apjEstregAphp;    
}
