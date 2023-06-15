package tech.ibrave.metabucket.application.metadata.restful.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataCategoryPersistenceReq;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataCategoryEntity;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryLiteDto;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Mapper
public interface MetadataCategoryMapper {
    MetadataCategory toDomain(MetadataCategoryLiteDto dto);
    MetadataCategory toDomain(MetadataCategoryEntity dto);
    MetadataCategoryDto toDto(MetadataCategoryEntity entity);
    MetadataCategory toDomain(MetadataCategoryPersistenceReq req);


}
