package com.makiia.crosscutting.persistence.entity;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apjmaeshomepama")
public class EntyApjmaeshomepama implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apj_ideunikey_aphp")
    private Integer  apjIdeunikeyAphp;

    @Basic(optional = false)
    @Column(name = "apj_nroreg_aphp")
    private String apjNroregAphp;

    @Basic(optional = false)
    @Column(name = "apj_titgru_aphp")
    private String apjTitgruAphp;

    @Basic(optional = false)
    @Column(name = "apjLnkgruAphp")
    private String apjLnkgruAphp;

    @Basic(optional = false)
    @Column(name = "apj_exlema_aphp")
    private String apjExlemaAphp;

    @Basic(optional = false)
    @Column(name = "apj_tipart_aphp")
    private String  apjTipartAphp;

    @Basic(optional = false)
    @Column(name = "rec_nroreg_reus")
    private String  recNroregReus;

    @Basic(optional = false)
    @Column(name = "sis_codpai_sipa")
    private String  sisCodpaiSipa;

    @Basic(optional = false)
    @Column(name = "sis_codpro_sipr")
    private String  sisCodproSipr;

    @Basic(optional = false)
    @Column(name = "sis_idedpt_sidp")
    private String  sisIdedptSidp;

    @Basic(optional = false)
    @Column(name = "apj_geoest_aphp")
    private String  apjGeoestAphp;

    @Basic(optional = false)
    @Column(name = "apj_geolat_aphp")
    private Float  apjGeolatAphp;

    @Basic(optional = false)
    @Column(name = "apj_geolon_aphp")
    private Float apjGeolonAphp;

    @Basic(optional = false)
    @Column(name = "apj_valtra_aphp")
    private Double  apjValtraAphp;

    @Basic(optional = false)
    @Column(name = "apj_valcal_aphp")
    private Double apjValcalAphp;

    @Basic(optional = false)
    @Column(name = "apj_valpun_aphp")
    private Double  apjValpunAphp;

    @Basic(optional = false)
    @Column(name = "apj_valpop_aphp")
    private Double  apjValpopAphp;

    @Basic(optional = false)
    @Column(name = "apj_totseg_aphp")
    private Integer  apjTotsegAphp;

    @Basic(optional = false)
    @Column(name = "apj_estaut_aphp")
    private String  apjEstautAphp;

    @Basic(optional = false)
    @Column(name = "apj_estges_aphp")
    private String  apjEstgesAphp;

    @Basic(optional = false)
    @Column(name = "apj_estdis_aphp")
    private String  apjEstdisAphp;

    @Basic(optional = false)
    @Column(name = "apj_estloc_aphp")
    private String  apjEstlocAphp;

    @Basic(optional = false)
    @Column(name = "apj_estpen_aphp")
    private String  apjEstpenAphp;

    @Basic(optional = false)
    @Column(name = "apj_peifec_aphp")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  apjPeifecAphp;

    @Basic(optional = false)
    @Column(name = "apj_peihor_aphp")
    private Float  apjPeihorAphp;

    @Basic(optional = false)
    @Column(name = "apj_peikey_aphp")
    private Integer  apjPeikeyAphp;

    @Basic(optional = false)
    @Column(name = "apj_peffec_aphp")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  apjPeffecAphp;

    @Basic(optional = false)
    @Column(name = "apj_pefhor_aphp")
    private Float  apjPefhorAphp;

    @Basic(optional = false)
    @Column(name = "apj_pefkey_aphp")
    private Integer  apjPefkeyAphp;

    @Basic(optional = false)
    @Column(name = "apj_legalr_aphp")
    private Integer  apjLegalrAphp;

    @Basic(optional = false)
    @Column(name = "apj_keyest_aphp")
    private String  apjKeyestAphp;

    @Basic(optional = false)
    @Column(name = "apj_keyloc_aphp")
    private String  apjKeylocAphp;

    @Basic(optional = false)
    @Column(name = "apj_keyage_aphp")
    private String  apjKeyageAphp;

    @Basic(optional = false)
    @Column(name = "apj_keyrai_aphp")
    private Integer  apjKeyraiAphp;

    @Basic(optional = false)
    @Column(name = "apj_keyraf_aphp")
    private Integer  apjKeyrafAphp;

    @Basic(optional = false)
    @Column(name = "sis_counta_rkey")
    private Integer  sisCountaRkey;

    @Basic(optional = false)
    @Column(name = "sis_countb_rkey")
    private Integer  sisCountbRkey;

    @Basic(optional = false)
    @Column(name = "sis_countc_rkey")
    private Integer  sisCountcRkey;

    @Basic(optional = false)
    @Column(name = "sis_countd_rkey")
    private Integer  sisCountdRkey;

    @Basic(optional = false)
    @Column(name = "sis_counte_rkey")
    private Integer  sisCounteRkey;

    @Basic(optional = false)
    @Column(name = "apj_estreg_aphp")
    private String  apjEstregAphp;


}
