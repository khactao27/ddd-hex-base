package tech.ibrave.metabucket.domain.metadata.usecase;

import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;
import tech.ibrave.metabucket.domain.shared.request.MetadataDefinitionSearchReq;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
public interface MetadataDefinitionUseCase extends BaseUseCase<MetadataDefinition, Long> {
    boolean existsByName(String name);

    Page<MetadataDefinitionDto> search(MetadataDefinitionSearchReq req);
}
