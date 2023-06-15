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
import tech.ibrave.metabucket.application.metadata.restful.facade.MetadataDefinitionFacade;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataDefinitionPersistenceReq;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionAuditingObject;
import tech.ibrave.metabucket.domain.shared.request.MetadataDefinitionSearchReq;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/metadata/definition")
public class MetadataDefinitionApi {
    public final MetadataDefinitionFacade definitionFacade;

    @PostMapping
    public SuccessResponse create(@Valid @RequestBody MetadataDefinitionPersistenceReq req) {
        return definitionFacade.create(req);
    }

    @PutMapping("/{id}")
    public SuccessResponse update(@PathVariable String id,
                                  @Valid @RequestBody MetadataDefinitionPersistenceReq req) {
        return definitionFacade.update(id, req);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse delete(@PathVariable String id) {
        return definitionFacade.delete(id);
    }

    @GetMapping("/{id}")
    public MetadataDefinitionAuditingObject getDetail(@PathVariable String id) {
        return definitionFacade.getDetail(id);
    }

    @GetMapping
    public Page<MetadataDefinitionAuditingObject> search(@ModelAttribute MetadataDefinitionSearchReq req) {
        return definitionFacade.search(req);
    }
}
