package tech.ibrave.metabucket.infra.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class SMTPMailSender {

    private Session session;
    private Transport transport;

    private void connect(SMTPMailConfig smtpConfig) {
        var props = new Properties();
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpConfig.getHostName());
        props.put("mail.smtp.port", smtpConfig.getPort());
        props.put("mail.smtp.socketFactory.port", smtpConfig.getPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.trust", "*");
        props.put("mail.smtp.ssl.checkserveridentity", "true");

        var authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpConfig.getUsername(),
                        smtpConfig.getPassword());
            }
        };
        this.session = Session.getInstance(props, authenticator);
    }
}

