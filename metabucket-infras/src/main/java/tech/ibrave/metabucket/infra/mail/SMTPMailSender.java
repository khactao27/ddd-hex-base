package tech.ibrave.metabucket.infra.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StringUtils;

@Slf4j
public class SMTPMailSender {

    private final SMTPMailConfig smtpConfig;
    private JavaMailSenderImpl mailSender;
    private boolean active;

    public SMTPMailSender(SMTPMailConfig smtpConfig) {
        this.smtpConfig = smtpConfig;
    }

    public void initMailSender() {
        try {
            this.mailSender = new JavaMailSenderImpl();
            this.mailSender.setHost(smtpConfig.getHostName());
            this.mailSender.setPort(Integer.parseInt(smtpConfig.getPort()));

            this.mailSender.setUsername(smtpConfig.getUsername());
            this.mailSender.setPassword(smtpConfig.getPassword());

            var props = this.mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", smtpConfig.getTransportProtocol());
            props.put("mail.smtp.auth", smtpConfig.isAuth());
            props.put("mail.smtp.starttls.enable", smtpConfig.isStarttls());
            props.put("mail.debug", smtpConfig.isDebug());
            props.put("mail.smtp.ssl.protocols", smtpConfig.getSslProtocol());
            props.put("mail.smtp.trust", "*");
            props.put("mail.smtp.ssl.checkserveridentity", smtpConfig.isCheckserveridentity());

            this.active = true;
            log.info("Mail service init successfully.");
        } catch (Exception e) {
            log.error("Mail service start failed.", e);
        }
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        if (active) {
            var message = new SimpleMailMessage();
            message.setFrom(smtpConfig.getFromAddress());
            message.setTo(to);
            message.setSubject(getSubjectWithPrefix(subject));
            message.setText(text);
            mailSender.send(message);
        } else {
            log.warn("Email service not ready, so mail can't be sent.");
        }
    }

    private String getSubjectWithPrefix(String rawSubject) {
        if (StringUtils.hasLength(smtpConfig.getEmailPrefix())) {
            return String.format("[%s] %s", smtpConfig.getEmailPrefix(), rawSubject);
        }
        return rawSubject;
    }
}

