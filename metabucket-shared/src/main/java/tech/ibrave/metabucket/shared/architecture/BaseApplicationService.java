package tech.ibrave.metabucket.shared.architecture;

import tech.ibrave.metabucket.shared.exception.ErrorCodeException;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public abstract class BaseApplicationService<DM, ID> implements BaseUseCase<DM,ID> {

    protected final BasePersistence<DM, ID> repo;

    protected BaseApplicationService(BasePersistence<DM, ID> repo) {
        this.repo = repo;
    }

    @Override
    public DM create(DM model) {
        return this.repo.save(model);
    }

    @Override
    public DM update(ID id, DM model) {
        return null;
    }

    @Override
    public void delete(ID id) {
        this.repo.delete(id);
    }

    public List<DM> saveAll(List<DM> models) {
        return this.repo.saveAll(models);
    }

    @Override
    public Optional<DM> getById(ID id) {
        return this.repo.findById(id);
    }

    @Override
    public DM getOrElseThrow(ID id) {
        return this.getById(id).orElseThrow(() -> new ErrorCodeException(this.notFound()));
    }
}
