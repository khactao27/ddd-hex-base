package tech.ibrave.metabucket.domain.metadata;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Getter
@Setter
public class MultiValueMetadata {
    private String id;
    private String value;
    private String description;
    private String metadataDefinitionId;
}
