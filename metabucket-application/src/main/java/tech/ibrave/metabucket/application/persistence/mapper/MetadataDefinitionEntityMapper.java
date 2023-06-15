package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataDefinitionEntity;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionAuditingObject;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Mapper
public interface MetadataDefinitionEntityMapper extends BaseEntityMapper<MetadataDefinitionEntity, MetadataDefinition>{
    MetadataDefinitionAuditingObject toDto(MetadataDefinitionEntity definition);
}
