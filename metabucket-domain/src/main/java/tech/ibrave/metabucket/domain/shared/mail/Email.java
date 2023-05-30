package tech.ibrave.metabucket.domain.shared.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * author: anct
 * date: 5/30/2023
 * YNWA
 */
@Getter
@Setter
public class Email {

    private String to;
    private String subject;
    private String body;
}
