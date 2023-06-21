package tech.ibrave.metabucket.application.storage.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchStorageReq;
import tech.ibrave.metabucket.domain.storage.Storage;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;
import tech.ibrave.metabucket.domain.storage.persistence.StoragePersistence;
import tech.ibrave.metabucket.domain.storage.usecase.StorageUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@ApplicationService
public class StorageService extends BaseApplicationService<Storage, Integer, StoragePersistence> implements StorageUseCase {
    protected StorageService(StoragePersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.STORAGE_NOT_FOUND;
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public Page<StorageDto> search(SearchStorageReq req) {
        return repo.search(req);
    }
}
