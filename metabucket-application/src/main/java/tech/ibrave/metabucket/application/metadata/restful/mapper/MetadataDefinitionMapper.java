package tech.ibrave.metabucket.application.metadata.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataDefinitionPersistenceReq;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Mapper
public interface MetadataDefinitionMapper {
    MetadataDefinition toDefinition(MetadataDefinitionDto dto);

    MetadataDefinition toDefinition(MetadataDefinitionPersistenceReq req);

    void updateDefinition(@MappingTarget MetadataDefinition metadataDefinition,
                          MetadataDefinitionPersistenceReq req);

    MetadataDefinitionDto toDto(MetadataDefinition definition);
}
