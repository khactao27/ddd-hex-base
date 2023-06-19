package tech.ibrave.metabucket.shared.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: hungnm
 * Date: 18/06/2023
 */
@Getter
@Setter
@SuppressWarnings("ALL")
public abstract class TreeViewResp<DM extends TreeNode> {
    @JsonIgnore
    private Set<DM> children;
    @JsonIgnore
    private Set<DM> roots;
    private List<DM> collections = new ArrayList<>();

    public TreeViewResp(List<DM> nodes) {

        this.children = (Set<DM>) nodes.stream().filter(TreeNode::isChildFolder).collect(Collectors.toSet());
        this.roots = (Set<DM>) nodes.stream().filter(TreeNode::isRootFolder).collect(Collectors.toSet());

        for (var root : roots) {
            var rootNode = buildRoot(root);
            buildTree(rootNode);
            collections.add((DM) rootNode);
        }
    }

    private void buildTree(DM root) {
        for (var child : children) {
            if (child.isYourParent(root.getId())) {
                var addedNode = addChildNode(root, child);
                buildTree(addedNode);
            }
        }
    }

    public DM buildRoot(DM rootNodeReference) {
        return createNode(rootNodeReference);
    }

    public DM addChildNode(DM parentNode, DM childNode) {
        var node = createNode(childNode);
        node.setParentId(childNode.getParentId());
        node.setParent(parentNode);
        parentNode.getChildren().add(node);
        return node;
    }

    protected abstract DM createNode(DM reference);
}
