package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataDefinitionEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Repository
@SuppressWarnings("all")
public interface MetadataDefinitionJpaRepository extends QueryDslRepository<MetadataDefinitionEntity, String> {
    boolean existsByName(String name);
    @Override
    @EntityGraph(value = "Definition.category", type = EntityGraph.EntityGraphType.LOAD)
    List<MetadataDefinitionEntity> findAll();

    @Override
    @EntityGraph(value = "Definition.category", type = EntityGraph.EntityGraphType.LOAD)
    MetadataDefinitionEntity save(MetadataDefinitionEntity entity);

    @Override
    @EntityGraph("Definition.category")
    Page<MetadataDefinitionEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(value = "Definition.category", type = EntityGraph.EntityGraphType.LOAD)
    Optional<MetadataDefinitionEntity> findById(@NonNull String id);
}
