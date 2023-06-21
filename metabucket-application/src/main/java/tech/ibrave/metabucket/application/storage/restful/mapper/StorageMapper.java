package tech.ibrave.metabucket.application.storage.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.storage.restful.request.StoragePersistenceReq;
import tech.ibrave.metabucket.domain.storage.Storage;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Mapper
public interface StorageMapper {
    StorageDto toDto(Storage storage);
    Storage toDomain(StoragePersistenceReq req);
    Storage update(@MappingTarget Storage storage, StoragePersistenceReq req);
}
