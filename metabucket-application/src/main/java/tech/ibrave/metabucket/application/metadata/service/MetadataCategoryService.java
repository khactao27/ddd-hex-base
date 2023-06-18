package tech.ibrave.metabucket.application.metadata.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.metadata.persistence.MetadataCategoryPersistence;
import tech.ibrave.metabucket.domain.metadata.usecase.MetadataCategoryUseCase;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@ApplicationService
public class MetadataCategoryService
        extends BaseApplicationService<MetadataCategory, Long, MetadataCategoryPersistence>
        implements MetadataCategoryUseCase {
    protected MetadataCategoryService(MetadataCategoryPersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }

    @Override
    public Page<MetadataCategoryDto> search(SearchMetadataCategoryReq req) {
        return repo.search(req);
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }
}
