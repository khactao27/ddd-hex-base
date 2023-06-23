package tech.ibrave.metabucket.domain.shared.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log extends BaseAuditingObject {
    private String id;
    private String message;
    private String detailLog;
    private LogSource source;
    private LogType type;
    private int severity; // to sorted
}
