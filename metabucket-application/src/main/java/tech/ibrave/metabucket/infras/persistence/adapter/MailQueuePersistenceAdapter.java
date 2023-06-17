package tech.ibrave.metabucket.infras.persistence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.shared.mail.Email;
import tech.ibrave.metabucket.domain.shared.mail.EmailSender;
import tech.ibrave.metabucket.domain.shared.mail.TemplateEmail;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.EmailQueueEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.EmailQueueJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.EmailQueueEntityMapper;

import java.util.Map;

/**
 * author: anct
 * date: 5/30/2023
 * YNWA
 */
@Component
@SuppressWarnings("all")
public class MailQueuePersistenceAdapter extends BaseJpaRepository<EmailQueueEntity, Email, String> implements EmailSender {

    protected MailQueuePersistenceAdapter(EmailQueueJpaRepository repo,
                                          EmailQueueEntityMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public void send(Email email) {
        this.save(email);
    }

    @Override
    public void send(TemplateEmail templateEmail) {
        var entity = mapper().fromTemplateEmail(templateEmail);
        repo().save(entity);
    }

    @Override
    public void send(TemplateEmail templateEmail, Map<String, Object> variables) {
        var entity = mapper().fromTemplateEmail(templateEmail, variables);
        repo().save(entity);
    }

    @Override
    public EmailQueueEntityMapper mapper() {
        return (EmailQueueEntityMapper) mapper;
    }

    @Override
    public EmailQueueJpaRepository repo() {
        return (EmailQueueJpaRepository) repo;
    }
}
