package tech.ibrave.metabucket.application.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.application.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Repository
@SuppressWarnings("all")
public interface UserJpaRepository extends QueryDslRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsAllByIdIn(List<String> ids);

    @EntityGraph(value = "User.group_roles", type = EntityGraph.EntityGraphType.LOAD)
    List<UserEntity> findByIdIn(List<String> ids);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity SET enable = :enable where id in :userIds")
    void updateStatusBulkUser(List<String> userIds, boolean enable);

    @EntityGraph(value = "User.group_roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(value = "User.group_roles", type = EntityGraph.EntityGraphType.LOAD)

    Optional<UserEntity> findByEmail(String email);
}
