package tech.ibrave.metabucket.infras.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.setting.usecase.SettingUseCase;
import tech.ibrave.metabucket.infras.mail.SMTPMailSender;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MailInitializer {

    private final SMTPMailSender smtpMailSender;
    private final SettingUseCase settingUseCase;

    public void init() {
        try {
            log.info("Init mail sender");
            var mailSetting = settingUseCase.getMailSetting();

            if (mailSetting == null) {
                log.warn("Not found smtp mail configuration");
                return;
            }

            smtpMailSender.init(mailSetting);
            smtpMailSender.start();

            log.info("Init mail sender done");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
