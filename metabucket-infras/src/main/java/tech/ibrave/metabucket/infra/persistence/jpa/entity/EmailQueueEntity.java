package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import tech.ibrave.metabucket.infra.persistence.jpa.entity.converter.Map2StringConverter;

import java.time.LocalDateTime;
import java.util.Map;

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
    @Column(length = 1000)
    private String subject;
    @Column(length = 10000)
    private String body;
    private String template;
    private boolean templatedEmail;
    @SuppressWarnings("all")
    @Convert(converter = Map2StringConverter.class)
    private Map<String, Object> variables;
    @Enumerated(EnumType.STRING)
    private MailStatus status = MailStatus.NEW;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime takenDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentDate;
}
