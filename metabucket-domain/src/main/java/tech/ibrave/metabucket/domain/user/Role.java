package tech.ibrave.metabucket.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.BaseAuditingObject;
import tech.ibrave.metabucket.domain.shared.Permission;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseAuditingObject {

    private Long id;
    private String name;
    private String description;
    private boolean enable;
    private List<Permission> permissions;
    private List<User> users;
}
