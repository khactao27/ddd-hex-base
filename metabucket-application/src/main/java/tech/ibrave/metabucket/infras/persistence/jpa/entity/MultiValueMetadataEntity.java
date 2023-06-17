package tech.ibrave.metabucket.infras.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_multi_value_metadata")
public class MultiValueMetadataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String value;
    private String description;
    @Column(name = "metadata_definition_id")
    private String metadataDefinitionId;
}
