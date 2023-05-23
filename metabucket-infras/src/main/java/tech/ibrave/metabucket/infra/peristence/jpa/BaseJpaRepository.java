package tech.ibrave.metabucket.infra.peristence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.BaseEntityMapper;
import tech.ibrave.metabucket.shared.domain.BasePersistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        var entities = models.stream()
                .map(mapper::fromDomainModel)
                .toList();

        return repo.saveAll(entities)
                .stream()
                .map(mapper::toDomainModel)
                .toList();
    }

    @Override
    public Optional<DM> findById(ID id) {
        return repo.findById(id).map(mapper::toDomainModel);
    }
}
