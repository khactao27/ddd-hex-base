package tech.ibrave.metabucket.application.metadata.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.metadata.MetadataOption;
import tech.ibrave.metabucket.domain.metadata.persistence.MetadataOptionPersistence;
import tech.ibrave.metabucket.domain.metadata.usecase.MetadataOptionUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@ApplicationService
public class MetadataOptionService
        extends BaseApplicationService<MetadataOption, Long, MetadataOptionPersistence>
        implements MetadataOptionUseCase {
    protected MetadataOptionService(MetadataOptionPersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }
}
