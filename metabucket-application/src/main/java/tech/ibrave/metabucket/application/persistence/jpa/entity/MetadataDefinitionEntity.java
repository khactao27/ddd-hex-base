package tech.ibrave.metabucket.application.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.ValueType;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_metadata_definition")
public class MetadataDefinitionEntity extends AbstractAuditingUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private ValueType valueType;
    private String description;
    @ManyToOne
    private MetadataCategoryEntity category;
}
