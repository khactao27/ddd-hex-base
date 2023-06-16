package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataDefinitionEntity;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Mapper(uses = {MetadataCategoryEntityMapper.class})
public interface MetadataDefinitionEntityMapper extends BaseEntityMapper<MetadataDefinitionEntity, MetadataDefinition>{
    MetadataDefinitionDto toDto(MetadataDefinitionEntity definition);

    @Override
    @Mapping(target = "category", qualifiedByName = "categoryLazy")
    MetadataDefinition toDomainModel(MetadataDefinitionEntity entity);

    @Named("definitionLazy")
    @Mapping(target = "category", ignore = true)
    MetadataDefinition toLazy(MetadataDefinitionEntity entity);

}
