package tech.ibrave.metabucket.infras.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.Permission;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.converter.Permissions2StringConverter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Entity
@Getter
@Setter
@SuppressWarnings("all")
@NamedEntityGraph(name = "Role.users", attributeNodes = {
        @NamedAttributeNode("users")
})
@Table(name = "tbl_role")
public class RoleEntity extends AbstractAuditingUserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean enable;

    @Convert(converter = Permissions2StringConverter.class)
    @Column(length = 5000)
    private List<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "tbl_user_role_mapping",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users = new HashSet<>();
}
