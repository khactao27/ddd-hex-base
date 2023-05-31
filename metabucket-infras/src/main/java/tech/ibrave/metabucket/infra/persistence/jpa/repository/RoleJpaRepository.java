package tech.ibrave.metabucket.infra.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Repository
@SuppressWarnings("all")
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    boolean existsByName(String name);

    RoleEntity findByName(String name);

    List<RoleEntity> findAllByNameContaining(String name);

    @Override
    @EntityGraph("Role.users")
    Optional<RoleEntity> findById(@NonNull Long id);

    void deleteAllByIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "UPDATE RoleEntity t SET t.enable = :enable WHERE t.id in :ids")
    void updateStatus(List<Long> ids, boolean enable);
}
