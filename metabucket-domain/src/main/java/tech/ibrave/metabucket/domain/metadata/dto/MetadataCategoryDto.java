package tech.ibrave.metabucket.domain.metadata.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.BaseAuditingObject;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Getter
@Setter
public class MetadataCategoryDto extends BaseAuditingObject {
    private Long id;
    private String name;
    private String description;
    private String parentId; // category parent

}
