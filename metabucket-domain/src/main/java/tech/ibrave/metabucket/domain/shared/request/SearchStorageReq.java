package tech.ibrave.metabucket.domain.shared.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.storage.StorageStatus;
import tech.ibrave.metabucket.domain.storage.StorageType;
import tech.ibrave.metabucket.shared.model.request.PageReq;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchStorageReq extends PageReq {
    private String name;
    private StorageType type;
    private StorageStatus status;
}
