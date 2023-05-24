package tech.ibrave.metabucket.shared.message;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
public class MessageSource extends ResourceBundleMessageSource {

    public String getRawMessage(String msgCode) {
        return this.getMessage(msgCode, null, LocaleContextHolder.getLocale());
    }

    public Message getMessage(String msgCode) {
        return new Message(msgCode, getRawMessage(msgCode));
    }
}
