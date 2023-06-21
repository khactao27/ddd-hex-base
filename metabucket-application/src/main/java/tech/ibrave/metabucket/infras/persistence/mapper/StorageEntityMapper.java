package tech.ibrave.metabucket.infras.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.storage.Storage;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.StorageEntity;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Mapper
public interface StorageEntityMapper extends BaseEntityMapper<StorageEntity, Storage> {
    StorageDto toDto(StorageEntity storage);
}
