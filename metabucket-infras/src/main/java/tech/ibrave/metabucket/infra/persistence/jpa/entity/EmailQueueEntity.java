package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.infra.mail.MailStatus;

/**
 * author: anct
 * date: 5/25/2023
 * YNWA
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_email_queue")
public class EmailQueueEntity extends AbstractAuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private MailStatus status = MailStatus.NEW;
}
