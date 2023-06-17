package tech.ibrave.metabucket;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration;
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
                ThymeleafAutoConfiguration.class,
                LdapRepositoriesAutoConfiguration.class,
                RedisRepositoriesAutoConfiguration.class,
                LdapAutoConfiguration.class,
                RedissonAutoConfiguration.class
        })
public class MetaBucketApplication {

    public static void main(String[] args) {
        var application = new SpringApplication(MetaBucketApplication.class);
        application.run(args);
    }
}
