package tech.ibrave.metabucket.infra.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tech.ibrave.metabucket.shared.message.MessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean
    @Primary
    public MessageSource messageSource() {
        var rs = new MessageSource();
        rs.setBasenames("i18n/message", "i18n/validations", "i18n/permissions");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @Bean
    public MessageSource permissionsSource() {
        var rs = new MessageSource();
        rs.setBasenames("i18n/permissions");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }
}