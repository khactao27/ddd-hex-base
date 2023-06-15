package tech.ibrave.metabucket.application.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}
