package tech.ibrave.metabucket.infra.peristence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Cache(region = "userCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity extends AbstractAuditingUserEntity {
    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String title;
    private String phone;
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    @ManyToMany
    private List<RoleEntity> roles;
    @ManyToMany
    private List<UserGroupEntity> groups;
    private boolean enable;
    private boolean enable2FA;
}
