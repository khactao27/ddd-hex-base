package tech.ibrave.metabucket.application.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.log.Log;
import tech.ibrave.metabucket.domain.shared.log.persistence.LogPersistence;
import tech.ibrave.metabucket.domain.shared.log.usecase.LogUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Slf4j
@ApplicationService
public class LogService extends BaseApplicationService<Log, String, LogPersistence> implements LogUseCase {
    private final List<Log> logs = new CopyOnWriteArrayList<>();

    protected LogService(LogPersistence repo) {
        super(repo);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void startBackgroundJob() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(this::persist,
                2000L, 3000L, TimeUnit.MILLISECONDS);

        log.info(">>>>>>>>>>Started log background service<<<<<<<");
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }

    public void persist() {
        if (logs.isEmpty()) return;

        var willPersistLogs = new LinkedList<Log>();
        logs.iterator().forEachRemaining(willPersistLogs::add);

        repo.saveAll(willPersistLogs);
        log.info("[Log background service] Flush {} logs", willPersistLogs.size());

        logs.removeAll(willPersistLogs);
    }

    @Override
    public void log(Log logInfo) {
        logs.add(logInfo);
    }
}
