package tech.ibrave.metabucket.application.setting.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.setting.restful.facade.SettingFacade;
import tech.ibrave.metabucket.domain.setting.SMTPMailSetting;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/settings")
public class SettingApi {

    private final SettingFacade facade;

    @PostMapping("/smtp-mail")
    public SuccessResponse updateMailSetting(@RequestBody @Valid SMTPMailSetting mailSetting) {
        return facade.updateMailSetting(mailSetting);
    }
}
