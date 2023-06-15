package tech.ibrave.metabucket.application.metadata.restful.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Getter
@Setter
public class DeleteMetadataDefinitionReq {
    private List<String> ids;
}
