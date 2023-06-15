package tech.ibrave.metabucket.domain.metadata.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Getter
@Setter
public class MultiValueMetadataDto {
    private String id;
    private String value;
    private String description;
}
