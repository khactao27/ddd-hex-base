package tech.ibrave.metabucket.application.metadata.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.dto.MetaDataDefinitionLiteDto;

import java.util.List;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataCategoryPersistenceReq {
    private String name;
    private String description;
    private String parentId; // category parent
    private List<MetaDataDefinitionLiteDto> metadataDefinitions;
}
