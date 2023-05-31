package tech.ibrave.metabucket.domain.setting.persistence;

import tech.ibrave.metabucket.domain.setting.Setting;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;

import java.util.Optional;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
public interface SettingPersistence extends BasePersistence<Setting, Integer> {
    Optional<Setting> findByCode(String code);
}
