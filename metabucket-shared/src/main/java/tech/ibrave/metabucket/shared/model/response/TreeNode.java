package tech.ibrave.metabucket.shared.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode<T> extends BaseAuditingObject {
    private T id;
    private T parentId;
    @JsonIgnore
    private TreeNode parent;
    private Boolean forceRootNode;
    private List<TreeNode> children = new ArrayList<>(5);

    public boolean isChildFolder() {
        return parentId != null;
    }

    public boolean isRootFolder() {
        if (forceRootNode != null) {
            return forceRootNode;
        }
        return parentId == null;
    }

    public boolean isYourParent(T id) {
        return this.parentId.equals(id);
    }
}
