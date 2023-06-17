package tech.ibrave.metabucket.infras.persistence.jpa.entity;

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

import java.io.Serializable;

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
public class SettingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    @Column(length = 10000)
    private String value;
}
