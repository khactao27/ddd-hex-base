package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataCategoryEntity;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Mapper
public interface MetadataCategoryEntityMapper extends BaseEntityMapper<MetadataCategoryEntity, MetadataCategory> {
    MetadataCategoryDto toDto(MetadataCategoryEntity entity);
}
