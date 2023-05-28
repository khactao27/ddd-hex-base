package tech.ibrave.metabucket.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "UPDATE RoleEntity t SET t.enable = :status WHERE t.id in :ids")
    void updateStatus(@Param("ids") List<Long> ids, @Param("status") boolean enable);
}
