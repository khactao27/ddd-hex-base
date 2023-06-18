package tech.ibrave.metabucket.application.metadata.restful.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataDefinitionPersistenceReq;
import tech.ibrave.metabucket.domain.metadata.MetadataOption;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Mapper
public interface MetadataOptionMapper {
    MetadataOption toDomainModel(MetadataDefinitionPersistenceReq.MetadataOption req);
}
