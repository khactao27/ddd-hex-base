package tech.ibrave.metabucket.domain.shared.mail;

import java.util.Map;

/**
 * author: anct
 * date: 5/30/2023
 * YNWA
 */
public interface EmailSender {

    void send(Email email);

    void send(TemplateEmail email);

    void send(TemplateEmail email, Map<String, Object> variables);
}
