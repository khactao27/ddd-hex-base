package tech.ibrave.metabucket.shared.domain;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public abstract class BaseUseCase<DM, ID> {

    protected final BasePersistence<DM, ID> repo;

    protected BaseUseCase(BasePersistence<DM, ID> repo) {
        this.repo = repo;
    }

    public DM save(DM model) {
        return this.repo.save(model);
    }

    public List<DM> saveAll(List<DM> models) {
        return this.repo.saveAll(models);
    }

    public Optional<DM> get(ID id) {
        return this.repo.findById(id);
    }
}
