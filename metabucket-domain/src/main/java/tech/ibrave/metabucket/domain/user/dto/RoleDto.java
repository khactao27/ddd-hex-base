package tech.ibrave.metabucket.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Author: nguyendinhthi
 * Date: 25/05/2023
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    private int id;
    private String name;
    private String description;

    private String createdBy;
    private String updatedBy;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;

}
