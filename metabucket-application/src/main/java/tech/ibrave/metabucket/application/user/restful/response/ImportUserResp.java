package tech.ibrave.metabucket.application.user.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 02/06/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportUserResp {
    private int numOfSuccess;
    private int numOfFail;
}
