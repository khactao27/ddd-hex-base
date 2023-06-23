package tech.ibrave.metabucket.infras.persistence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.shared.log.Log;
import tech.ibrave.metabucket.domain.shared.log.persistence.LogPersistence;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.LogEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.LogJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.LogEntityMapper;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Component
public class LogPersistenceAdapter extends BaseJpaRepository<LogEntity, Log, String> implements LogPersistence {
    protected LogPersistenceAdapter(LogJpaRepository repo,
                                    LogEntityMapper mapper) {
        super(repo, mapper);
    }
}
