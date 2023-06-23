package tech.ibrave.metabucket.infras.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.shared.log.Log;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.LogEntity;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Mapper
public interface LogEntityMapper extends BaseEntityMapper<LogEntity, Log> {
}
