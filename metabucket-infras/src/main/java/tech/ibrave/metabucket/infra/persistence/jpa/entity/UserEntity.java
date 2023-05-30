package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import tech.ibrave.metabucket.domain.shared.UserSource;

import java.util.List;

/**
 * Author: hungnm
 * Date: 24/05/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_user")
@NamedEntityGraph(name = "User.group_roles", attributeNodes = {
        @NamedAttributeNode("groups")
})
@Cache(region = "userCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity extends AbstractAuditingUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String phone;
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<RoleEntity> roles;
    @ManyToMany
    @JoinTable(name = "tbl_user_group_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<UserGroupEntity> groups;
    private boolean enable;
    private boolean enable2FA;
}
