package tech.ibrave.metabucket.application.user.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Author: hungnm
 * Date: 04/06/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserExportFieldsResp {
    private List<String> fields;
}
