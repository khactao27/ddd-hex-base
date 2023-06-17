package tech.ibrave.metabucket.infras.persistence.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.domain.shared.mail.Email;
import tech.ibrave.metabucket.domain.shared.mail.TemplateEmail;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.EmailQueueEntity;

import java.util.Map;

/**
 * Author: anct
 * Date: 30/05/2023
 */
@Mapper
public interface EmailQueueEntityMapper extends BaseEntityMapper<EmailQueueEntity, Email> {

    EmailQueueEntity fromTemplateEmail(TemplateEmail templateEmail);

    default EmailQueueEntity fromTemplateEmail(TemplateEmail templateEmail, Map<String, Object> variables) {
        var entity = fromVariables(variables);
        updateEmailEntity(entity, templateEmail);
        return entity;
    }

    default EmailQueueEntity fromVariables(Map<String, Object> variables) {
        var objectMapper = new ObjectMapper();
        return objectMapper.convertValue(variables, EmailQueueEntity.class);
    }

    void updateEmailEntity(@MappingTarget EmailQueueEntity emailQueueEntity, TemplateEmail templateEmail);

}
