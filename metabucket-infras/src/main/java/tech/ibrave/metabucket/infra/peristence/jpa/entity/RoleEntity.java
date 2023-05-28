package tech.ibrave.metabucket.infra.peristence.jpa.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.converter.Permissions2StringConverter;
import tech.ibrave.metabucket.shared.constant.Permission;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Entity
@Getter
@Setter
@SuppressWarnings("all")
@Table(name = "tbl_role")
@Cache(region = "roleCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleEntity extends AbstractAuditingUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean enable;

    @Convert(converter = Permissions2StringConverter.class)
    private List<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_user_role_mapping",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;
}
