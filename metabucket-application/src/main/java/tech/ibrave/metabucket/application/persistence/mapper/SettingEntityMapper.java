package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.persistence.jpa.entity.SettingEntity;
import tech.ibrave.metabucket.domain.setting.Setting;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Mapper
public interface SettingEntityMapper extends BaseEntityMapper<SettingEntity, Setting> {
}
