package tech.ibrave.metabucket.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@SpringBootApplication
public class MetaBucketApplication {

    public static void main(String[] args) {
        var application = new SpringApplication(MetaBucketApplication.class);
        application.run(args);
    }
}
