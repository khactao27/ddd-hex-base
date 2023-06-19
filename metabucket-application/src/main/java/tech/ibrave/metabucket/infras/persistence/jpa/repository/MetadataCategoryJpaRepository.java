package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataCategoryEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Repository
@SuppressWarnings("all")
public interface MetadataCategoryJpaRepository extends QueryDslRepository<MetadataCategoryEntity, Long> {
    Page<MetadataCategoryEntity> findAllByNameContaining(String name, Pageable pageable);
    boolean existsByName(String name);

    @Override
    @EntityGraph(value = "Category.definitions", type = EntityGraph.EntityGraphType.LOAD)
    List<MetadataCategoryEntity> findAll();

    @Override
    @EntityGraph(value = "Category.definitions", type = EntityGraph.EntityGraphType.LOAD)
    MetadataCategoryEntity save(MetadataCategoryEntity entity);

    @Override
    @EntityGraph("Category.definitions")
    Page<MetadataCategoryEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(value = "Category.definitions", type = EntityGraph.EntityGraphType.LOAD)
    Optional<MetadataCategoryEntity> findById(@NonNull Long id);

    @Query("SELECT f.id FROM MetadataCategoryEntity f where f.parentId = ?1")
    List<Long> findAllChildrenId(Long parentId);

    @Query("SELECT f FROM MetadataCategoryEntity f where f.parentId = ?1")
    List<MetadataCategoryEntity> findAllChildren(Long parentId);
}
