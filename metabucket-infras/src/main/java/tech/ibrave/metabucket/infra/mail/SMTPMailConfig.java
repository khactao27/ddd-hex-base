package tech.ibrave.metabucket.infra.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * author: anct
 * date: 5/25/2023
 * YNWA
 */
@Getter
@Setter
public class SMTPMailConfig {

    private String hostName;

    private String username;

    private String password;

    private String port;

    private String fromAddress;

    private boolean tls;

    private long timeout;

    private String emailPrefix;
}
