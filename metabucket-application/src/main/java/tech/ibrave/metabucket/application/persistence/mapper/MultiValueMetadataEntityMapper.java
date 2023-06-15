package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MultiValueMetadataEntity;
import tech.ibrave.metabucket.domain.metadata.MultiValueMetadata;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Mapper
public interface MultiValueMetadataEntityMapper extends BaseEntityMapper<MultiValueMetadataEntity, MultiValueMetadata> {
}
