package tech.ibrave.metabucket.application.persistence.adapter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.persistence.jpa.BaseDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataDefinitionEntity;
import tech.ibrave.metabucket.application.persistence.jpa.entity.QMetadataCategoryEntity;
import tech.ibrave.metabucket.application.persistence.jpa.entity.QMetadataDefinitionEntity;
import tech.ibrave.metabucket.application.persistence.jpa.repository.MetadataDefinitionJpaRepository;
import tech.ibrave.metabucket.application.persistence.mapper.MetadataDefinitionEntityMapper;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionAuditingObject;
import tech.ibrave.metabucket.domain.metadata.persistence.MetadataDefinitionPersistence;
import tech.ibrave.metabucket.domain.shared.request.MetadataDefinitionSearchReq;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Component
@SuppressWarnings("all")
public class MetadataDefinitionPersistenceAdapter
        extends BaseDslRepository<MetadataDefinitionEntity, MetadataDefinition, String>
        implements MetadataDefinitionPersistence {
    protected MetadataDefinitionPersistenceAdapter(MetadataDefinitionJpaRepository repo,
                                                   MetadataDefinitionEntityMapper mapper,
                                                   EntityManager em) {
        super(repo, mapper, em);
    }

    @Override
    public QMetadataDefinitionEntity entityPath() {
        return QMetadataDefinitionEntity.metadataDefinitionEntity;
    }

    @Override
    public boolean existsByName(String name) {
        return repo().existsByName(name);
    }

    @Override
    public Page<MetadataDefinitionAuditingObject> search(MetadataDefinitionSearchReq req) {
        var query = buildBasicQuery();
        if (StringUtils.isNotEmpty(req.getCategoryId())) {
            buildQueryWithJoinCategory(query, req.getCategoryId());
        }
        var whereBuilder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(req.getName())) {
            whereBuilder.and(QMetadataDefinitionEntity.metadataDefinitionEntity.name.likeIgnoreCase("%" + req.getName() + "%"));
        }
        if (req.getValueType() != null) {
            whereBuilder.and(QMetadataDefinitionEntity.metadataDefinitionEntity.valueType.eq(req.getValueType()));
        }

        query.where(whereBuilder);
        if (StringUtils.isNotEmpty(req.getSort())) {
            query.orderBy(getSortSpecifiers(req));
        }
        return new Page(getDomainResultAsPage(query, mapper()::toDto, req));
    }

    public JPAQuery<MetadataDefinitionEntity> buildBasicQuery() {
        return queryFactory
                .select(QMetadataDefinitionEntity.metadataDefinitionEntity)
                .from(QMetadataDefinitionEntity.metadataDefinitionEntity);
    }

    public void buildQueryWithJoinCategory(JPAQuery<MetadataDefinitionEntity> query, String getCategoryId) {
        query.innerJoin(QMetadataDefinitionEntity.metadataDefinitionEntity.category, QMetadataCategoryEntity.metadataCategoryEntity)
                .where(QMetadataCategoryEntity.metadataCategoryEntity.id.eq(getCategoryId));
    }

    @Override
    public MetadataDefinitionEntityMapper mapper() {
        return super.mapper();
    }

    @Override
    @SuppressWarnings("all")
    public MetadataDefinitionJpaRepository repo() {
        return super.repo();
    }

}
