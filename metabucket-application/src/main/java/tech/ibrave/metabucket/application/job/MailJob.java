package tech.ibrave.metabucket.application.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.EmailQueueJpaRepository;

import java.time.LocalDateTime;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MailJob {

    private final EmailQueueJpaRepository repo;

    @Scheduled(cron = "0 0/15 * * * *")
    public void releaseTakenMail() {
        try {
            var fromTakenDate = LocalDateTime.now().minusMinutes(15);
            log.info("Run release taken mail before {}", fromTakenDate);
            repo.relaseTakenMail(fromTakenDate);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 0 0 1/2 * ?")
    public void clearSentMail() {
        try {
            var fromSentDate = LocalDateTime.now().minusDays(2);
            log.info("Clean sent mail before {}", fromSentDate);
            repo.cleanSentMail(fromSentDate);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
