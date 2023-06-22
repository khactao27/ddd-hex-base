package tech.ibrave.metabucket.application.metadata.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import tech.ibrave.metabucket.application.metadata.restful.facade.MetadataCategoryFacade;
import tech.ibrave.metabucket.application.metadata.restful.request.DeleteMetadataCategoryReq;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataCategoryPersistenceReq;
import tech.ibrave.metabucket.application.metadata.restful.response.MetadataCategoryTreeViewResp;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/metadata/category")
public class MetadataCategoryApi {
    private final MetadataCategoryFacade categoryFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('mb.category.create')")
    public SuccessResponse create(@Valid @RequestBody MetadataCategoryPersistenceReq req) {
        return categoryFacade.create(req);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('mb.category.update')")
    public SuccessResponse update(@PathVariable Long id,
                                  @Valid @RequestBody MetadataCategoryPersistenceReq req) {
        return categoryFacade.update(id, req);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('mb.category.delete')")
    public SuccessResponse delete(@ModelAttribute DeleteMetadataCategoryReq req) {
        return categoryFacade.delete(req);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('mb.category.view')")
    public MetadataCategoryDto getDetail(@PathVariable Long id) {
        return categoryFacade.getDetail(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('mb.category.view')")
    public MetadataCategoryTreeViewResp search(@ModelAttribute SearchMetadataCategoryReq req) {
        return categoryFacade.search(req);
    }
}
