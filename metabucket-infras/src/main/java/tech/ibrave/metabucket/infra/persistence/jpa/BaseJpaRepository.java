package tech.ibrave.metabucket.infra.persistence.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.ibrave.metabucket.infra.persistence.mapper.BaseEntityMapper;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.request.PageReq;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Slf4j
@SuppressWarnings("all")
public abstract class BaseJpaRepository<E, DM, ID> implements BasePersistence<DM, ID> {

    protected final JpaRepository<E, ID> repo;
    protected final BaseEntityMapper<E, DM> mapper;

    protected BaseJpaRepository(JpaRepository<E, ID> repo,
                                BaseEntityMapper<E, DM> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public <R> R repo() {
        return (R) repo;
    }

    public <M> M mapper() {
        return (M) mapper;
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
    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    @Override
    public List<DM> findAllById(List<ID> ids) {
        return CollectionUtils.toList(repo.findAllById(ids), mapper::toDomainModel);
    }

    @Override
    public Page<DM> findAll(PageReq pageRequest) {
        var pageable = PageRequest.of(pageRequest.getPageIndex(), pageRequest.getPageSize());
        var page = repo.findAll(pageable);
        return new Page<>(pageRequest.getPageIndex(),
                pageRequest.getPageSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                CollectionUtils.toList(page.getContent(), mapper::toDomainModel));
    }

    @Override
    public boolean existsById(ID id) {
        return repo.existsById(id);
    }

    public <T, R> Page<R> toPage(List<T> content,
                                 Function<T, R> function,
                                 int pageIndex,
                                 int pageSize) {
        var result = CollectionUtils.toList(content, function);
        var page = new PageImpl<>(result);
        return new Page<>(
                pageIndex,
                pageSize,
                page.getTotalElements(),
                page.getTotalPages(),
                result
        );
    }

    public <T, R> Page<R> toPage(List<T> content,
                                 Function<T, R> function,
                                 PageReq pageRequest) {
        var result = CollectionUtils.toList(content, function);
        var page = new PageImpl<>(result);
        return new Page<>(
                pageRequest.getPageIndex(),
                pageRequest.getPageSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                result
        );
    }

    public Sort getSort(PageReq req) {
        try {
            var orders = new ArrayList<Sort.Order>();
            for (var sort : req.getSorts().entrySet()) {
                if (sort.getValue()) {//if sort asc
                    orders.add(Sort.Order.asc(sort.getKey()));
                } else {
                    orders.add(Sort.Order.desc(sort.getKey()));
                }
            }
            return Sort.by(orders);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return Sort.unsorted();
    }
}
