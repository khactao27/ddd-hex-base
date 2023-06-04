package tech.ibrave.metabucket.application.persistence.jpa.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tech.ibrave.metabucket.application.auth.base.SecurityContext;

import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackages = "tech.ibrave.metabucket.application.persistence.jpa.entity")
@EnableJpaRepositories(basePackages = "tech.ibrave.metabucket.application.persistence.jpa.repository")
public class JpaDataSourceConfig {

    private final SecurityContext securityContext;

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            try {
                var auditor = securityContext.isAuthenticated()
                        ? securityContext.getUser().getId()
                        : StringUtils.EMPTY;

                return Optional.of(auditor);
            } catch (Exception e) {
                return Optional.empty();
            }
        };
    }
}
