package tech.ibrave.metabucket.application.persistence.jpa.entity;

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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
import java.util.Set;


/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Group.users", attributeNodes = {
        @NamedAttributeNode("users")
})
@Table(name = "tbl_user_group")
@Cache(region = "userGroupCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupEntity extends AbstractAuditingUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private boolean enable;
    @ManyToMany
    @JoinTable(name = "tbl_user_group_mapping",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users;
}

