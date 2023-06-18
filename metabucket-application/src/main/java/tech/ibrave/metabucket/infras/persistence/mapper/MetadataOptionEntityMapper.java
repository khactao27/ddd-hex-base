package tech.ibrave.metabucket.infras.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.metadata.MetadataOption;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataOptionEntity;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Mapper
public interface MetadataOptionEntityMapper extends BaseEntityMapper<MetadataOptionEntity, MetadataOption> {
}
