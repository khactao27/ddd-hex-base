package tech.ibrave.metabucket.infra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import tech.ibrave.metabucket.shared.annotation.UseCase;

@Configuration
@ComponentScan(
        basePackages = "tech.ibrave.metabucket.domain",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class)
)
public class DomainConfig {
}