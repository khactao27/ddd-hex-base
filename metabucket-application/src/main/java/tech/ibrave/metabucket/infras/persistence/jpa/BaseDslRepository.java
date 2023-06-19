package tech.ibrave.metabucket.infras.persistence.jpa;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.ibrave.metabucket.infras.persistence.mapper.BaseEntityMapper;
import tech.ibrave.metabucket.shared.model.request.PageReq;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Author: hungnm
 * Date: 28/05/2023
 */
@Slf4j
public abstract class BaseDslRepository<E, DM, ID> extends BaseJpaRepository<E, DM, ID> {

    protected final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    protected BaseDslRepository(QueryDslRepository<E, ID> repo,
                                BaseEntityMapper<E, DM> mapper) {
        super(repo, mapper);
        this.queryFactory = new JPAQueryFactory(em);
    }

    protected BaseDslRepository(QueryDslRepository<E, ID> repo,
                                BaseEntityMapper<E, DM> mapper,
                                EntityManager em) {
        super(repo, mapper);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<E> query(Sort.Order... orders) {
        return query(null, orders);
    }

    public Page<E> query(Integer offset, Integer limit, Sort.Order... orders) {
        return query(null, offset, limit, orders);
    }

    public Page<E> query(Predicate predicate, Sort.Order... orders) {
        return query(predicate, null, null, orders);
    }

    public Page<E> query(Predicate predicate, Integer offset, Integer limit, Sort.Order... orders) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 20;
        }
        var pageable = PageRequest.of(Math.max(offset, 0), limit, Sort.by(orders));
        return query(predicate, pageable);
    }

    public Page<E> query(Predicate predicate, Pageable pageable) {
        return predicate != null ? dslRepo().findAll(predicate, pageable) : repo.findAll(pageable);
    }

    public List<E> all(Sort.Order... orders) {
        return all(null, orders);
    }

    public List<E> all(Predicate predicate, Sort.Order... orders) {
        List<E> results = new LinkedList<>();

        Page<E> page = query(predicate, orders);
        while (page.hasContent()) {
            results.addAll(page.getContent());
            if (results.size() >= page.getTotalElements()) {
                break;
            }
            Pageable nextPageable = page.getPageable().next();
            if (predicate != null) {
                page = dslRepo().findAll(predicate, nextPageable);
            } else {
                page = repo.findAll(nextPageable);
            }
        }
        return results;
    }

    public <T> List<T> fetch(JPQLQuery<T> query, long skip, long limit) {
        return query.fetch()
                .stream()
                .skip(skip)
                .limit(limit)
                .toList();
    }

    public <T> List<T> fetch(JPQLQuery<T> query, int pageIndex, int pageSize) {
        return query.fetch()
                .stream()
                .skip(Math.max((pageIndex - 1) * pageSize, 0))
                .limit(pageSize)
                .toList();
    }

    public List<E> fetch(JPQLQuery<E> query, Pageable pageable) {
        return query.fetch()
                .stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();
    }

    public Page<E> getResultAsPage(JPQLQuery<E> query,
                                   int pageSize,
                                   int pageIndex) {
        // build pageable, count
        var pageable = PageRequest.of(Math.max(pageIndex - 1, 0), pageSize);
        var result = fetch(query, pageable);
        var total = fetchCount(query);

        return new PageImpl<>(result, pageable, total);
    }

    public <T> Page<T> getDomainResultAsPage(JPQLQuery<E> query,
                                             Function<E, T> function,
                                             PageReq pageReq) {
        // build pageable, count
        var pageable = PageRequest.of(Math.max(pageReq.getPageIndex() - 1, 0), pageReq.getPageSize());
        var result = CollectionUtils.toList(fetch(query, pageable), function);
        var total = fetchCount(query);
        return new PageImpl<>(result, pageable, total);
    }

    public <T> long fetchCount(JPQLQuery<T> query) {
        return query.fetchCount();
    }

    @SuppressWarnings("all")
    public OrderSpecifier<?>[] getSortSpecifiers(PageReq req) {
        var orders = new ArrayList<OrderSpecifier<?>>(req.getSorts().size());

        try {
            for (var sort : req.getSorts().entrySet()) {
                Path<Object> fieldPath = Expressions.path(Object.class, entityPath(), sort.getKey());
                orders.add(new OrderSpecifier(sort.getValue() == PageReq.Order.ASC ? Order.ASC : Order.DESC, fieldPath));
            }

            return orders.toArray(new OrderSpecifier[]{});
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new OrderSpecifier[]{};
    }

    public abstract <EP extends EntityPathBase<E>> EP entityPath();

    @SuppressWarnings("all")
    public QueryDslRepository<E, ID> dslRepo() {
        return super.repo();
    }
}
