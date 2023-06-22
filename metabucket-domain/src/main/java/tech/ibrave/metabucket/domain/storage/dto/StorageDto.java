package tech.ibrave.metabucket.domain.storage.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.storage.StorageStatus;
import tech.ibrave.metabucket.domain.storage.StorageType;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Getter
@Setter
public class StorageDto {
    private String name;
    private String description;
    private StorageType type;
    private long totalCapacity;
    private long currentCapacity;
    private StorageStatus status;
}
