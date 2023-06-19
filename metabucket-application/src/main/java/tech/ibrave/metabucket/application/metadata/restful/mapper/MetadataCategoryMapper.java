package tech.ibrave.metabucket.application.metadata.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataCategoryPersistenceReq;
import tech.ibrave.metabucket.application.metadata.restful.response.MetadataCategoryTreeView;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryLiteDto;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataCategoryEntity;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Mapper
public interface MetadataCategoryMapper {
    MetadataCategory toDomain(MetadataCategoryLiteDto dto);
    MetadataCategory toDomain(MetadataCategoryEntity dto);
    MetadataCategoryDto toDto(MetadataCategoryEntity entity);
    MetadataCategoryDto toDto(MetadataCategory entity);
    MetadataCategory toDomain(MetadataCategoryPersistenceReq req);
    MetadataCategoryTreeView toTreeView(MetadataCategoryDto dto);
    void updateCategory(@MappingTarget MetadataCategory category, MetadataCategoryPersistenceReq req);

}
