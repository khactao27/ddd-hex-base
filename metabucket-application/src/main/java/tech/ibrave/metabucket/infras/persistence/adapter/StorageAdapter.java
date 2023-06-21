package tech.ibrave.metabucket.infras.persistence.adapter;

import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.shared.request.SearchStorageReq;
import tech.ibrave.metabucket.domain.storage.Storage;
import tech.ibrave.metabucket.domain.storage.dto.StorageDto;
import tech.ibrave.metabucket.domain.storage.persistence.StoragePersistence;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.QStorageEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.StorageEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.StorageEntityJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.StorageEntityMapper;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Component
@SuppressWarnings("ALL")
public class StorageAdapter extends BaseDslRepository<StorageEntity, Storage, Integer> implements StoragePersistence {
    protected StorageAdapter(StorageEntityJpaRepository repo,
                             StorageEntityMapper mapper,
                             EntityManager em) {
        super(repo, mapper, em);
    }

    @Override
    public QStorageEntity entityPath() {
        return QStorageEntity.storageEntity;
    }

    @Override
    public boolean existsByName(String name) {
        return repo().existsByName(name);
    }

    @Override
    public Page<StorageDto> search(SearchStorageReq req) {
        var query = queryFactory.select(QStorageEntity.storageEntity)
                .from(QStorageEntity.storageEntity);
        var whereBuilder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(req.getName())) {
            whereBuilder.and(QStorageEntity.storageEntity.name.likeIgnoreCase("%" + req.getName() + "%"));
        }
        if (req.getType() != null) {
            whereBuilder.and(QStorageEntity.storageEntity.type.eq(req.getType()));
        }
        if (req.getStatus() != null) {
            whereBuilder.and(QStorageEntity.storageEntity.status.eq(req.getStatus()));
        }
        return new Page(getDomainResultAsPage(query, mapper()::toDto, req));
    }

    @Override
    public StorageEntityJpaRepository repo() {
        return super.repo();
    }

    @Override
    public StorageEntityMapper mapper() {
        return super.mapper();
    }
}
