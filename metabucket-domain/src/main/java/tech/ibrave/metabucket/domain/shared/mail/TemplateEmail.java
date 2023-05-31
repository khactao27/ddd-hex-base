package tech.ibrave.metabucket.domain.shared.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateEmail {
    private String to;
    private String subject;
    private String template;
    private Map<String, Object> variables;
}
