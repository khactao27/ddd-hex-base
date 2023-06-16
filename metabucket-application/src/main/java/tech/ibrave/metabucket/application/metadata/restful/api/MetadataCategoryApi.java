package tech.ibrave.metabucket.application.metadata.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.metadata.restful.facade.MetadataCategoryFacade;
import tech.ibrave.metabucket.application.metadata.restful.request.DeleteMetadataCategoryReq;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataCategoryPersistenceReq;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

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
    public SuccessResponse create(@Valid @RequestBody MetadataCategoryPersistenceReq req) {
        return categoryFacade.create(req);
    }

    @PutMapping("/{id}")
    public SuccessResponse update(@PathVariable String id,
                                  @Valid @RequestBody MetadataCategoryPersistenceReq req) {
        return categoryFacade.update(id, req);
    }

    @DeleteMapping
    public SuccessResponse delete(@ModelAttribute DeleteMetadataCategoryReq req) {
        return categoryFacade.delete(req);
    }

    @GetMapping("/{id}")
    public MetadataCategoryDto getDetail(@PathVariable String id) {
        return categoryFacade.getDetail(id);
    }

    @GetMapping
    public Page<MetadataCategoryDto> search(@ModelAttribute SearchMetadataCategoryReq req) {
        return categoryFacade.search(req);
    }
}
