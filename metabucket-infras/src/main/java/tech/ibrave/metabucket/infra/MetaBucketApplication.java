package tech.ibrave.metabucket.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@SpringBootApplication(
        scanBasePackages = "tech.ibrave.metabucket",
        exclude = {
                MailSenderAutoConfiguration.class,
                ThymeleafAutoConfiguration.class
        })
public class MetaBucketApplication {

    public static void main(String[] args) {
        var application = new SpringApplication(MetaBucketApplication.class);
        application.run(args);
    }
}
