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

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_metadata_option")
@Cache(region = "MetadataOption", usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetadataOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private String description;
    @Column(name = "metadata_definition_id")
    private Long metadataDefinitionId;
}
