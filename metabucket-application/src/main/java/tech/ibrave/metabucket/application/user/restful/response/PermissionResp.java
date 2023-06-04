package tech.ibrave.metabucket.application.user.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author: anct
 * date: 6/4/2023
 * YNWA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResp {
    private String name;

    private String code;
}
