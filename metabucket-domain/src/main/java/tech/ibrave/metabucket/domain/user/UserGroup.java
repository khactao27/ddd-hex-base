package tech.ibrave.metabucket.domain.user;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.architecture.annotation.SortableField;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Getter
@Setter
public class UserGroup extends BaseAuditingObject {
    private String id;
    @SortableField
    private String name;
    private boolean enable;
    private String description;
    private Set<User> users;
}