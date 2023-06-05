package tech.ibrave.metabucket.application.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import tech.ibrave.metabucket.application.auth.base.SecurityContext;

import java.util.Optional;

/**
 * Author: hungnm
 * Date: 05/06/2023
 */
@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfig {
    private final SecurityContext securityContext;
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            try {
                var auditor = securityContext.isAuthenticated()
                        ? securityContext.getUser().getUsername()
                        : StringUtils.EMPTY;

                return Optional.of(auditor);
            } catch (Exception e) {
                return Optional.of("System");
            }
        };
    }
}
