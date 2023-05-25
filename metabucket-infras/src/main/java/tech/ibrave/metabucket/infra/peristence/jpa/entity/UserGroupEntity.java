package tech.ibrave.metabucket.infra.peristence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_user_group")
@Cache(region = "userCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupEntity extends AbstractAuditingUserEntity {
    @Id
    private String id;
    private String name;
    private String description;
}
