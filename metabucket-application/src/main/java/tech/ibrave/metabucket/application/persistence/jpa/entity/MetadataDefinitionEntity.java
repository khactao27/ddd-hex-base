package tech.ibrave.metabucket.application.persistence.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.ValueType;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Definition.category", attributeNodes = {
        @NamedAttributeNode("category")
})
@Table(name = "tbl_metadata_definition")
public class MetadataDefinitionEntity extends AbstractAuditingUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private ValueType valueType;
    private String description;
    @JoinColumn(name = "metadata_definition_id")
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MultiValueMetadataEntity> multiValues;
    @ManyToOne
    @JoinTable(name = "tbl_definition_category_mapping",
            joinColumns = @JoinColumn(name = "metadata_definition_id"),
            inverseJoinColumns = @JoinColumn(name = "metadata_category_id")
    )
    private MetadataCategoryEntity category;
}
