package tech.ibrave.metabucket.application.metadata.restful.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryLiteDto;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionDto;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Mapper
public interface MetadataCategoryMapper {
    MetadataCategory toCategory(MetadataDefinitionDto dto);
    MetadataCategory toCategory(MetadataCategoryLiteDto dto);

}
