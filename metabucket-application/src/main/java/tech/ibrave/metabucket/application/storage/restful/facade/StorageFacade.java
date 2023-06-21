package tech.ibrave.metabucket.application.storage.restful.facade;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.storage.restful.mapper.StorageMapper;
import tech.ibrave.metabucket.application.storage.restful.request.StoragePersistenceReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchStorageReq;
import tech.ibrave.metabucket.domain.storage.StorageStatus;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;
import tech.ibrave.metabucket.domain.storage.usecase.StorageUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Component
@RequiredArgsConstructor
public class StorageFacade {
    private final StorageUseCase storageUseCase;
    private final StorageMapper storageMapper;
    private final MessageSource messageSource;

    public SuccessResponse create(StoragePersistenceReq req) {
        var storage = storageMapper.toDomain(req);
        validateExistsName(req.getName());
        validateStorage(req);
        storage.setStatus(StorageStatus.AVAILABLE);
        storage.setCurrentCapacity(0);
        var id = storageUseCase.save(storage).getId();
        return new SuccessResponse(id, messageSource.getMessage("mb.storage.create.success"));
    }

    public SuccessResponse update(Integer id, StoragePersistenceReq req) {
        var storage = storageUseCase.getOrElseThrow(id);
        if (!storage.getName().equals(req.getName())) {
            validateExistsName(req.getName());
        }
        if (storage.getType() != req.getType() || StringUtils.isNotEmpty(req.getAccessToken())
                || StringUtils.isNotEmpty(req.getRefreshToken())) {
            validateStorage(req);
        }
        storageMapper.update(storage, req);
        return new SuccessResponse(id, messageSource.getMessage("{mb.storage.update.success}"));
    }

    public StorageDto getDetail(Integer id) {
        var storage = storageUseCase.getOrElseThrow(id);
        return storageMapper.toDto(storage);
    }

    public Page<StorageDto> search(SearchStorageReq req) {
        return storageUseCase.search(req);
    }

    private void validateStorage(StoragePersistenceReq req) {
        //todo: validate capacity
    }

    private void validateExistsName(String name) {
        if (storageUseCase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.DUPLICATE_STORAGE_NAME);
        }
    }

}
