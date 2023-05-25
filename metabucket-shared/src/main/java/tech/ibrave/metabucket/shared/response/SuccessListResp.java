package tech.ibrave.metabucket.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessListResp {

    private Integer numberOfSuccesses;
    private Integer numberOfFailures;
    private List<SuccessResponse> successResponses;
}
