package tech.ibrave.metabucket.domain.metadata;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.BaseAuditingObject;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Getter
@Setter
public class MetadataDefinition extends BaseAuditingObject {
    private String id;
    private String name;
    private ValueType valueType;
    private String description;
    private MetadataCategory category;
    private Set<MultiValueMetadata> multiValues;

}
