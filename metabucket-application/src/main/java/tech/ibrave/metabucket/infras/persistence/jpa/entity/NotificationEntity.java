package tech.ibrave.metabucket.infras.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_notification")
public class NotificationEntity extends AbstractAuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    @Column(length = 10000)
    private String message;
    private String actionLink;
    private String receiver; // can be user group or single user
    private boolean readStatus;
}
