package tech.ibrave.metabucket.infra.peristence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.BaseEntityMapper;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public abstract class BaseJpaRepository<E, DM, ID> implements BasePersistence<DM, ID> {

    protected JpaRepository<E, ID> repo;
    protected BaseEntityMapper<E, DM> mapper;

    protected BaseJpaRepository(JpaRepository<E, ID> repo,
                                BaseEntityMapper<E, DM> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public DM save(DM model) {
        return mapper.toDomainModel(repo.save(mapper.fromDomainModel(model)));
    }

    @Override
    public List<DM> saveAll(List<DM> models) {
        var entities = CollectionUtils.toList(models, mapper::fromDomainModel);
        return CollectionUtils.toList(repo.saveAll(entities), mapper::toDomainModel);
    }

    @Override
    public Optional<DM> findById(ID id) {
        return repo.findById(id).map(mapper::toDomainModel);
    }

    @Override
    public void delete(ID id) {
        repo.deleteById(id);
    }

    @Override
    public List<DM> findAllById(List<ID> ids) {
        return CollectionUtils.toList(repo.findAllById(ids), mapper::toDomainModel);
    }
}
