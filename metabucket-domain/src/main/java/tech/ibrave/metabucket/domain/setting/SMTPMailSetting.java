package tech.ibrave.metabucket.domain.setting;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * author: anct
 * date: 5/25/2023
 * YNWA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SMTPMailSetting {

    private String hostName;
    private String username;
    private String password;
    private String port;
    private String fromAddress;
    private boolean tls = true;
    private long timeout = 10000; // milliseconds
    private String emailPrefix;
    private boolean debug;
    private String transportProtocol = "smtp";
    private boolean auth = true;
    private boolean starttls = true;
    private String sslProtocol = "TLSv1.2";
    private boolean checkserveridentity = true;
}
