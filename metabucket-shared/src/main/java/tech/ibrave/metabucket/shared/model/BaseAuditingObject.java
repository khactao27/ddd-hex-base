package tech.ibrave.metabucket.shared.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.architecture.annotation.SortableField;

import java.time.LocalDateTime;

/**
 * author: anct
 * date: 5/28/2023
 * YNWA
 */
@Getter
@Setter
public class BaseAuditingObject {
    private String createdBy;
    private String updatedBy;

    @SortableField
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @SortableField
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
}
