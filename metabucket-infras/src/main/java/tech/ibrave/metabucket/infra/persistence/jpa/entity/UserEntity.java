package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import jakarta.persistence.CascadeType;
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
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.infra.persistence.jpa.constant.TableConstants;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String title;
    private String phone;
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = TableConstants.TBL_USER_ROLE_MAPPING,
            joinColumns = @JoinColumn(name = TableConstants.Default.USER_ID),
            inverseJoinColumns = @JoinColumn(name = TableConstants.Default.ROLE_ID)
    )
    private List<RoleEntity> roles;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = TableConstants.TBL_USER_GROUP_MAPPING,
            joinColumns = @JoinColumn(name = TableConstants.Default.USER_ID),
            inverseJoinColumns = @JoinColumn(name = TableConstants.Default.GROUP_ID)
    )
    private List<UserGroupEntity> groups;
    private boolean enable;
    private boolean enable2FA;
}
