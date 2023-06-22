package tech.ibrave.metabucket.domain.storage;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Getter
@Setter
public class Storage extends BaseAuditingObject {
    private Integer id;
    private String name;
    private String description;
    private StorageType type;
    private long totalCapacity;
    private long currentCapacity;
    private StorageStatus status;
    private String accessToken;
    private String refreshToken;
}
