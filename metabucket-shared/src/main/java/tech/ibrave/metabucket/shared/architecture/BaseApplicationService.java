package tech.ibrave.metabucket.shared.architecture;

import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.request.PageReq;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public abstract class BaseApplicationService<DM, ID, R extends BasePersistence<DM, ID>> implements BaseUseCase<DM, ID> {

    protected final R repo;

    protected BaseApplicationService(R repo) {
        this.repo = repo;
    }

    @Override
    public DM save(DM model) {
        return this.repo.save(model);
    }

    @Override
    public DM update(ID id, Consumer<DM> consumerModel) {
        var entity = getOrElseThrow(id);
        consumerModel.accept(entity);
        return this.repo.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        this.repo.deleteById(id);
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

    @Override
    public Page<DM> findAll(PageReq pageRequest) {
        return this.repo.findAll(pageRequest);
    }

    @Override
    public Page<DM> findAll(int pageIndex, int pageSize) {
        return this.repo.findAll(new PageReq(Math.max(pageIndex - 1, 0), pageSize));
    }

    @Override
    public void existByIdOrElseThrow(ID id) {
        if (!existById(id)) {
            throw new ErrorCodeException(this.notFound());
        }
    }

    @Override
    public boolean existById(ID id) {
        return this.repo.existsById(id);
    }

    @Override
    public DM deleteIfExist(ID id) {
        var model = getOrElseThrow(id);
        deleteById(id);
        return model;
    }

    @Override
    public void deleteAllByIdInBatch(List<ID> ids) {
        repo.deleteAllByIdInBatch(ids);
    }
}
