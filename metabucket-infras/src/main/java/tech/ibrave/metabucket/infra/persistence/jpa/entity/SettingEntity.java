package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Author: anct
 * Date: 25/05/2023
 * #YWNA
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_setting")
@Cache(region = "settingCache", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SettingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    @Column(length = 10000)
    private String value;
}
