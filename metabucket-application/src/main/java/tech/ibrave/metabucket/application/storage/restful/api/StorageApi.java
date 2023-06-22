package tech.ibrave.metabucket.application.storage.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.storage.restful.facade.StorageFacade;
import tech.ibrave.metabucket.application.storage.restful.request.DeleteStorageReq;
import tech.ibrave.metabucket.application.storage.restful.request.StoragePersistenceReq;
import tech.ibrave.metabucket.domain.shared.request.SearchStorageReq;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/storage")
public class StorageApi {
    private final StorageFacade storageFacade;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SuccessResponse create(@Valid @RequestBody StoragePersistenceReq req) {
        return storageFacade.create(req);
    }

    @PutMapping("/{id}")
    SuccessResponse update(@PathVariable Integer id,
                           @Valid @RequestBody StoragePersistenceReq req) {
        return storageFacade.update(id, req);
    }

    @GetMapping("/{id}")
    StorageDto getDetail(@PathVariable Integer id) {
        return storageFacade.getDetail(id);
    }

    @GetMapping
    Page<StorageDto> search(@ModelAttribute SearchStorageReq req) {
        return storageFacade.search(req);
    }

    @DeleteMapping
    SuccessResponse delete(@Valid @ModelAttribute DeleteStorageReq req) {
        return storageFacade.delete(req);
    }
}
