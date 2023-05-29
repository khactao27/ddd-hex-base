package tech.ibrave.metabucket.shared.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
@Getter
public enum Permission {
    NONE("", "none"),
    // manage user
    VIEW_USER("", "mb.users.view"),
    CREATE_USER("", "mb.users.create"),
    UPDATE_USER("", "mb.users.update"),
    DELETE_USER("", "mb.users.delete"),

    // manage group
    VIEW_GROUP("", ""),
    CREATE_GROUP("", ""),
    UPDATE_GROUP("", ""),
    DELETE_GROUP("", ""),

    // manage role
    VIEW_ROLE("", ""),
    CREATE_ROLE("", ""),
    UPDATE_ROLE("", ""),
    DELETE_ROLE("", "");

    private final String name;
    private final String value;

    Permission(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static Optional<Permission> of(String value) {
        return Arrays.stream(values())
                .filter(v -> v.getValue().equals(value))
                .findFirst();
    }
}
