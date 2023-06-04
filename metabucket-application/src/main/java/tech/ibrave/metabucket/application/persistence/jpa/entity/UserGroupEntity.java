package tech.ibrave.metabucket.application.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_user_group")
@Cache(region = "userGroupCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupEntity extends AbstractAuditingUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "groups")
    private List<UserEntity> users;
}
