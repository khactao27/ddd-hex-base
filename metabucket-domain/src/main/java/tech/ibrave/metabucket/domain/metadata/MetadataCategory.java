package tech.ibrave.metabucket.domain.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetadataCategory {
    private String id;
    private String name;
    private String description;
    private String parentId; // category parent

    public MetadataCategory(String id) {
        this.id = id;
    }
}
