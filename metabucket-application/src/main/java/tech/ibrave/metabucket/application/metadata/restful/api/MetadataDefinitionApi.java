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
import tech.ibrave.metabucket.application.metadata.restful.facade.MetadataDefinitionFacade;
import tech.ibrave.metabucket.application.metadata.restful.request.DeleteMetadataDefinitionReq;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataDefinitionPersistenceReq;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;
import tech.ibrave.metabucket.domain.shared.request.MetadataDefinitionSearchReq;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

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
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('mb.metadata.create')")
    public SuccessResponse create(@Valid @RequestBody MetadataDefinitionPersistenceReq req) {
        return definitionFacade.create(req);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('mb.metadata.update')")
    public SuccessResponse update(@PathVariable Long id,
                                  @Valid @RequestBody MetadataDefinitionPersistenceReq req) {
        return definitionFacade.update(id, req);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyAuthority('mb.metadata.delete')")
    public SuccessResponse delete(@ModelAttribute DeleteMetadataDefinitionReq req) {
        return definitionFacade.delete(req);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('mb.metadata.view')")
    public MetadataDefinitionDto getDetail(@PathVariable Long id) {
        return definitionFacade.getDetail(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('mb.metadata.view')")
    public Page<MetadataDefinitionDto> search(@ModelAttribute MetadataDefinitionSearchReq req) {
        return definitionFacade.search(req);
    }
}
