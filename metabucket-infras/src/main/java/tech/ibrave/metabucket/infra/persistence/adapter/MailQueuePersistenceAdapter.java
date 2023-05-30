package tech.ibrave.metabucket.infra.persistence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.shared.mail.Email;
import tech.ibrave.metabucket.domain.shared.mail.EmailSender;
import tech.ibrave.metabucket.infra.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.EmailQueueEntity;
import tech.ibrave.metabucket.infra.persistence.jpa.repository.EmailQueueJpaRepository;
import tech.ibrave.metabucket.infra.persistence.mapper.EmailQueueEntityMapper;

/**
 * author: anct
 * date: 5/30/2023
 * YNWA
 */
@Component
public class MailQueuePersistenceAdapter extends BaseJpaRepository<EmailQueueEntity, Email, String> implements EmailSender {

    protected MailQueuePersistenceAdapter(EmailQueueJpaRepository repo,
                                          EmailQueueEntityMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public void send(Email email) {
        this.save(email);
    }
}
