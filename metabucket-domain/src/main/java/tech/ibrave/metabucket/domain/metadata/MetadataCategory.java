package tech.ibrave.metabucket.domain.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Getter
@Setter
public class MetadataCategory {
    private String id;
    private String name;
    private String description;
    private String parentId; // category parent
    private Set<MetadataDefinition> metadataDefinitions;
}
