package tech.ibrave.metabucket.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Getter
@Setter
public class UserGroup {
    private String id;
    private String name;
    private String description;
    private List<User> users;
}