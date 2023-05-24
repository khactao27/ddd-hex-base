package tech.ibrave.metabucket.application.healthcheck.restful.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/public")
public class HealthCheckApi {

    private final MessageSource messageSource;

    @GetMapping("/ping")
    public SuccessResponse ping() {
        return SuccessResponse.ofMessage(messageSource.getRawMessage("mb.pong"));
    }
}
