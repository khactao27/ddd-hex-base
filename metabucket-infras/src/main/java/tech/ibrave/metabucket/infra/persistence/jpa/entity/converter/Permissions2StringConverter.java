package tech.ibrave.metabucket.infra.persistence.jpa.entity.converter;

import tech.ibrave.metabucket.shared.constant.Permission;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
public class Permissions2StringConverter extends Enums2StringConverter<Permission> {

    public Permissions2StringConverter() {
        super(Permission.class);
    }
}

