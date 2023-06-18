package tech.ibrave.metabucket.application.metadata.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: hungnm
 * Date: 16/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteMetadataCategoryReq {
    private List<Long> ids;
}
