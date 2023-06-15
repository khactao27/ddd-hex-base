package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataDefinitionEntity;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Mapper
public interface MetadataDefinitionEntityMapper extends BaseEntityMapper<MetadataDefinitionEntity, MetadataDefinition>{
    MetadataDefinitionDto toDto(MetadataDefinitionEntity definition);
}
