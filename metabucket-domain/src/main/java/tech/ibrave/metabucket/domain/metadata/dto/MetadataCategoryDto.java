package tech.ibrave.metabucket.domain.metadata.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

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
    private Long parentId; // category parent

}
