package tech.ibrave.metabucket.application.metadata.restful.response;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.dto.MetaDataDefinitionLiteDto;
import tech.ibrave.metabucket.shared.model.response.TreeNode;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 19/06/2023
 */
@Getter
@Setter
public class MetadataCategoryTreeView extends TreeNode<Long> {
    private String name;
    private String description;
    private Long workspaceId;
    private Set<MetaDataDefinitionLiteDto> metadataDefinitions;

}
