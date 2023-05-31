package tech.ibrave.metabucket.infra.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.shared.mail.Email;
import tech.ibrave.metabucket.domain.shared.mail.TemplateEmail;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.EmailQueueEntity;

import java.util.Map;

/**
 * Author: anct
 * Date: 30/05/2023
 */
@Mapper
public interface EmailQueueEntityMapper extends BaseEntityMapper<EmailQueueEntity, Email> {

    EmailQueueEntity fromTemplateEmail(TemplateEmail templateEmail);

    EmailQueueEntity fromTemplateEmail(TemplateEmail templateEmail, Map<String, Object> variables);
}
