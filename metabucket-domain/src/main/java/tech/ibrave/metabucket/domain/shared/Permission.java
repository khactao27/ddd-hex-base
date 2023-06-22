package tech.ibrave.metabucket.domain.shared;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
@Getter
public enum Permission {
    NONE("none"),
    // manage user
    VIEW_USER("mb.users.view"),
    CREATE_USER("mb.users.create"),
    UPDATE_USER("mb.users.update"),
    DELETE_USER("mb.users.delete"),
    RESET_PASSWORD("mb.users.delete.resetpassword"),


    // manage group
    VIEW_GROUP("mb.groups.view"),
    CREATE_GROUP("mb.groups.create"),
    UPDATE_GROUP("mb.groups.update"),
    DELETE_GROUP("mb.groups.delete"),

    // manage role
    VIEW_ROLE("mb.roles.view"),
    CREATE_ROLE("mb.roles.create"),
    UPDATE_ROLE("mb.roles.update"),
    DELETE_ROLE("mb.roles.delete"),

    // manage category
    VIEW_CATEGORY("mb.category.view"),
    CREATE_CATEGORY("mb.category.create"),
    UPDATE_CATEGORY("mb.category.update"),
    DELETE_CATEGORY("mb.category.delete"),

    // manage metadata definition
    VIEW_METADATA("mb.metadata.view"),
    CREATE_METADATA("mb.metadata.create"),
    UPDATE_METADATA("mb.metadata.update"),
    DELETE_METADATA("mb.metadata.delete"),

    // manage storage
    VIEW_STORAGE("mb.storage.view"),
    CREATE_STORAGE("mb.storage.create"),
    UPDATE_STORAGE("mb.storage.update"),
    DELETE_STORAGE("mb.storage.delete");


    private final String value;

    Permission(String value) {
        this.value = value;
    }

    public static Optional<Permission> of(String value) {
        return Arrays.stream(values())
                .filter(v -> v.getValue().equals(value))
                .findFirst();
    }
}
