package tech.ibrave.metabucket.infras.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tech.ibrave.metabucket.domain.storage.StorageStatus;
import tech.ibrave.metabucket.domain.storage.StorageType;

import java.io.Serializable;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_storage")
@Cache(region = "storageCache", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StorageEntity extends AbstractAuditingUserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private StorageType type;
    private int totalCapacity;
    private int currentCapacity;
    @Enumerated(EnumType.STRING)
    private StorageStatus status;
    private String accessToken;
    private String refreshToken;
}
