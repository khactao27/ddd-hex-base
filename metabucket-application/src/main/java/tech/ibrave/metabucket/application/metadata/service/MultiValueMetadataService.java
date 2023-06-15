package tech.ibrave.metabucket.application.metadata.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.metadata.MultiValueMetadata;
import tech.ibrave.metabucket.domain.metadata.persistence.MultiValueMetadataPersistence;
import tech.ibrave.metabucket.domain.metadata.usecase.MultiValueMetadataUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@ApplicationService
public class MultiValueMetadataService
        extends BaseApplicationService<MultiValueMetadata, String, MultiValueMetadataPersistence>
        implements MultiValueMetadataUseCase {
    protected MultiValueMetadataService(MultiValueMetadataPersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }
}
