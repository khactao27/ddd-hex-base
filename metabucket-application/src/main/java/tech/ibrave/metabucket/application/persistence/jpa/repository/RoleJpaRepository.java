package tech.ibrave.metabucket.application.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.application.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Repository
@SuppressWarnings("all")
public interface RoleJpaRepository extends QueryDslRepository<RoleEntity, Long> {

    boolean existsByName(String name);

    RoleEntity findByName(String name);

    @EntityGraph("Role.users")
    Page<RoleEntity> findAllByNameContaining(String name, Pageable pageable);

    @Override
    @EntityGraph("Role.users")
    List<RoleEntity> findAll();

    @Override
    @EntityGraph("Role.users")
    Page<RoleEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph("Role.users")
    Optional<RoleEntity> findById(@NonNull Long id);

    void deleteAllByIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "UPDATE RoleEntity t SET t.enable = :enable WHERE t.id in :ids")
    void updateStatus(List<Long> ids, boolean enable);

    boolean existsByNameAndIdNot(String name, Long id);
}
