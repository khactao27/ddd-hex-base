package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.infra.mail.MailStatus;

import java.time.LocalDateTime;

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
    @Column(name = "fromAddress")
    private String from;
    @Column(name = "toAddress")
    private String to;
    private String subject;
    private String body;
    @Enumerated(EnumType.STRING)
    private MailStatus status = MailStatus.NEW;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime takenDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentDate;
}
