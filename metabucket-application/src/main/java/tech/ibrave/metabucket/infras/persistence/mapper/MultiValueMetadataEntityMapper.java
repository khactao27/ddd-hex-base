package tech.ibrave.metabucket.infras.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.metadata.MultiValueMetadata;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MultiValueMetadataEntity;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Mapper
public interface MultiValueMetadataEntityMapper extends BaseEntityMapper<MultiValueMetadataEntity, MultiValueMetadata> {
}
