package tech.ibrave.metabucket.infra.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.setting.Setting;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.SettingEntity;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Mapper
public interface SettingEntityMapper extends BaseEntityMapper<SettingEntity, Setting> {
}
