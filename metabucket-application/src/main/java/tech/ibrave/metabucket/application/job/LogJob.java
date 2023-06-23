package tech.ibrave.metabucket.application.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.LogJpaRepository;

import java.time.LocalDateTime;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogJob {

    private final LogJpaRepository repo;

    @Scheduled(cron = "0 0 0 1/2 * ?")
    public void clearBeforeWeekLog() {
        try {
            var fromDate = LocalDateTime.now().minusDays(7);
            repo.cleanLog(fromDate);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
