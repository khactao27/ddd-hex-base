package tech.ibrave.metabucket.infra.peristence.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_role")
public class RoleEntity extends AbstractAuditingUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
