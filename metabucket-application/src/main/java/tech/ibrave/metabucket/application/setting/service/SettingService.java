package tech.ibrave.metabucket.application.setting.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.setting.SMTPMailSetting;
import tech.ibrave.metabucket.domain.setting.Setting;
import tech.ibrave.metabucket.domain.setting.persistence.SettingPersistence;
import tech.ibrave.metabucket.domain.setting.usecase.SettingUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;

import java.util.Optional;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@ApplicationService
public class SettingService extends BaseApplicationService<Setting, Integer, SettingPersistence>
        implements SettingUseCase {

    private final ObjectMapper objectMapper;

    protected SettingService(SettingPersistence repo,
                             ObjectMapper objectMapper) {
        super(repo);
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public SMTPMailSetting getMailSetting() {
        var settingOptional = findByCode(Setting.Code.SMTP_SETTING);

        if (settingOptional.isPresent()) {
            return objectMapper.readValue(settingOptional.get().getValue(), SMTPMailSetting.class);
        }

        return null;
    }

    @Override
    @SneakyThrows
    public Setting saveMailSetting(SMTPMailSetting mailSetting) {
        var setting = findByCode(Setting.Code.SMTP_SETTING).orElse(new Setting());
        setting.setCode(Setting.Code.SMTP_SETTING);
        setting.setValue(objectMapper.writeValueAsString(mailSetting));
        return repo.save(setting);
    }

    @Override
    public Optional<Setting> findByCode(String code) {
        return repo.findByCode(code);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }
}
