package tech.ibrave.metabucket.infra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import tech.ibrave.metabucket.shared.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.annotation.DomainService;

@Configuration
@ComponentScan(
        basePackages = {
                "tech.ibrave.metabucket.domain",
                "tech.ibrave.metabucket.application"
        },
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ApplicationService.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = DomainService.class)
        }
)
public class ServiceConfig {
}