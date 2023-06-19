package tech.ibrave.metabucket.application.setting.restful.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.setting.SMTPMailSetting;
import tech.ibrave.metabucket.domain.setting.usecase.SettingUseCase;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Component
@RequiredArgsConstructor
public class SettingFacade {

    private final MessageSource messageSource;
    private final SettingUseCase settingUseCase;
    private final ApplicationEventPublisher eventPublisher;

    public SuccessResponse updateMailSetting(SMTPMailSetting mailSetting) {
        var setting = settingUseCase.saveMailSetting(mailSetting);

        eventPublisher.publishEvent(mailSetting);

        return new SuccessResponse(setting.getCode(), messageSource.getMessage("mb.settings.create.success"));
    }
}
