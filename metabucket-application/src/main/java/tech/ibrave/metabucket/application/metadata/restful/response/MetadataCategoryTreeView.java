package tech.ibrave.metabucket.application.metadata.restful.response;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.response.TreeNode;

/**
 * Author: hungnm
 * Date: 19/06/2023
 */
@Getter
@Setter
public class MetadataCategoryTreeView extends TreeNode<Long> {
    private String name;
    private String description;
}
