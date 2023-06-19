package tech.ibrave.metabucket.application.metadata.restful.response;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.response.TreeViewResp;

import java.util.List;

/**
 * Author: hungnm
 * Date: 19/06/2023
 */
@Getter
@Setter
public class MetadataCategoryTreeViewResp extends TreeViewResp<MetadataCategoryTreeView> {


    public MetadataCategoryTreeViewResp(List<MetadataCategoryTreeView> nodes) {
        super(nodes);
    }

    @Override
    protected MetadataCategoryTreeView createNode(MetadataCategoryTreeView reference) {
        var node = new MetadataCategoryTreeView();
        node.setName(reference.getName());
        node.setDescription(reference.getDescription());
        node.setParentId(reference.getParentId());
        node.setId(reference.getId());
        return node;
    }
}
