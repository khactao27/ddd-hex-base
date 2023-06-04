package tech.ibrave.metabucket.application.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Author: anct
 * Date: 30/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationInitializer {

    private final MailInitializer mailInitializer;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationStarted() {
        log.info("=============Init components after application started=============");
        mailInitializer.init();
    }
}
