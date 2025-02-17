package tech.ibrave.metabucket.infras.persistence.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.BaseEntityMapper;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.SortableField;
import tech.ibrave.metabucket.shared.model.request.PageReq;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.lang.reflect.AnnotatedParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    protected Map<String, Boolean> sortableFields;

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

    public Class<DM> domainClass() {
        var genericInterface = ((AnnotatedParameterizedType) this.getClass().getAnnotatedSuperclass())
                .getAnnotatedActualTypeArguments()[1];
        return (Class<DM>) genericInterface.getType();
    }

    @EventListener(ApplicationStartedEvent.class)
    public void initSortableFields() {
        this.sortableFields = new HashMap<>(5);
        Class<?> dmClass = domainClass();
        log.info("Init sortable fields on domain model {}", dmClass.getSimpleName());
        while (dmClass != null) {
            for (var field : dmClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(SortableField.class)) {
                    this.sortableFields.put(field.getName(), true);
                }
            }
            dmClass = dmClass.getSuperclass();
        }
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

    @Override
    public void deleteAllByIdInBatch(List<ID> ids) {
        repo.deleteAllByIdInBatch(ids);
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
                if (isSortable(sort.getKey())) {
                    if (sort.getValue() == PageReq.Order.ASC) {
                        orders.add(Sort.Order.asc(sort.getKey()));
                    } else if (sort.getValue() == PageReq.Order.DESC) {
                        orders.add(Sort.Order.desc(sort.getKey()));
                    }
                }
            }
            return Sort.by(orders);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return Sort.unsorted();
    }

    public boolean isSortable(String field) {
        return CollectionUtils.isNotEmpty(sortableFields)
                && this.sortableFields.containsKey(field);
    }

    public <T, R> Page<R> toPage(List<T> content,
                                 Function<T, R> function,
                                 Pageable pageable) {
        var result = CollectionUtils.toList(content, function);
        var page = new PageImpl<>(result);
        return new Page<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                result
        );
    }

    public <T> Page<T> toPage(List<T> content, Pageable pageable) {
        var page = new PageImpl<>(content);
        return new Page<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                content
        );
    }

    public Pageable getPageable(PageReq pageRequest) {
        return PageRequest.of(Math.max(pageRequest.getPageIndex() - 1, 0), pageRequest.getPageSize());
    }
}
