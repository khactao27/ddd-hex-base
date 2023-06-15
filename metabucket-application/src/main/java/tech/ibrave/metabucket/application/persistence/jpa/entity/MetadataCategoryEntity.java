package tech.ibrave.metabucket.application.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_metadata_category")
public class MetadataCategoryEntity extends AbstractAuditingUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String parentId; // category parent
    @OneToMany
    @JoinTable(name = "tbl_definition_category_mapping",
            joinColumns = @JoinColumn(name = "metadata_category_id"),
            inverseJoinColumns = @JoinColumn(name = "metadata_definition_id")
    )
    private Set<MetadataDefinitionEntity> metadataDefinitions;
}
