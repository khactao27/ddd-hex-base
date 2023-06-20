package tech.ibrave.metabucket.shared.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: hungnm
 * Date: 18/06/2023
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TreeNode<T> extends BaseAuditingObject {
    private T id;
    private T parentId;
    @JsonIgnore
    private TreeNode parent;
    @JsonIgnore
    private Boolean forceRootNode;
    private List<TreeNode> children = new ArrayList<>(5);

    @JsonIgnore
    public boolean isChildFolder() {
        return parentId != null;
    }

    @JsonIgnore
    public boolean isRootFolder() {
        if (forceRootNode != null) {
            return forceRootNode;
        }
        return parentId == null;
    }

    @JsonIgnore
    public boolean isYourParent(T id) {
        return this.parentId.equals(id);
    }
}
