package tech.ibrave.metabucket.application.metadata.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;
import tech.ibrave.metabucket.domain.metadata.persistence.MetadataDefinitionPersistence;
import tech.ibrave.metabucket.domain.metadata.usecase.MetadataDefinitionUseCase;
import tech.ibrave.metabucket.domain.shared.request.MetadataDefinitionSearchReq;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@ApplicationService
public class MetadataDefinitionService
        extends BaseApplicationService<MetadataDefinition, Long, MetadataDefinitionPersistence>
        implements MetadataDefinitionUseCase {
    protected MetadataDefinitionService(MetadataDefinitionPersistence repo) {
        super(repo);
    }

    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public Page<MetadataDefinitionDto> search(MetadataDefinitionSearchReq req) {
        return repo.search(req);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }
}
