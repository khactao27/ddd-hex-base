package tech.ibrave.metabucket.domain.storage.usecase;

import tech.ibrave.metabucket.domain.shared.request.SearchStorageReq;
import tech.ibrave.metabucket.domain.storage.Storage;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
public interface StorageUseCase extends BaseUseCase<Storage, Integer> {
    boolean existsByName(String name);

    Page<StorageDto> search(SearchStorageReq req);
}
