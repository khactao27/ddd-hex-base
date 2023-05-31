package tech.ibrave.metabucket.domain.shared.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author: anct
 * date: 5/30/2023
 * YNWA
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    private String to;
    private String subject;
    private String body;
}
