package tech.ibrave.metabucket.infras.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataCategoryEntity;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Mapper(uses = {MetadataDefinitionEntityMapper.class})
public interface MetadataCategoryEntityMapper extends BaseEntityMapper<MetadataCategoryEntity, MetadataCategory> {
    MetadataCategoryDto toDto(MetadataCategoryEntity entity);

    @Override
    @Mapping(target = "metadataDefinitions", qualifiedByName = "definitionLazy")
    MetadataCategory toDomainModel(MetadataCategoryEntity entity);

    @Named("categoryLazy")
    @Mapping(target = "metadataDefinitions", ignore = true)
    MetadataCategory toLazy(MetadataCategoryEntity entity);
}
