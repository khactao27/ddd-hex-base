package tech.ibrave.metabucket.domain.shared.request;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.ValueType;
import tech.ibrave.metabucket.shared.model.request.PageReq;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Getter
@Setter
public class MetadataDefinitionSearchReq extends PageReq {
    private String name;
    private ValueType valueType;
    private Long categoryId;
}
