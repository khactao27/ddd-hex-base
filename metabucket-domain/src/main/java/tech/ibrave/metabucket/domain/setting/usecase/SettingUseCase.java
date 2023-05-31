package tech.ibrave.metabucket.domain.setting.usecase;

import tech.ibrave.metabucket.domain.setting.SMTPMailSetting;
import tech.ibrave.metabucket.domain.setting.Setting;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;

import java.util.Optional;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
public interface SettingUseCase extends BaseUseCase<Setting, Integer> {

    SMTPMailSetting getMailSetting();

    Setting saveMailSetting(SMTPMailSetting mailSetting);

    Optional<Setting> findByCode(String code);
}
