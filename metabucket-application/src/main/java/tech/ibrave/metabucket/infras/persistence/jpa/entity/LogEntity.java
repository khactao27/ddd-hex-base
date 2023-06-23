package tech.ibrave.metabucket.infras.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.log.LogSource;
import tech.ibrave.metabucket.domain.shared.log.LogType;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_log")
public class LogEntity extends AbstractAuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String traceId;
    private String message;
    private String detailLog;
    @Enumerated(EnumType.STRING)
    private LogSource source;
    @Enumerated(EnumType.STRING)
    private LogType type;
    private int severity; // to sorted
}
