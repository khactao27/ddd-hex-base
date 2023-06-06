package tech.ibrave.metabucket.application.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.application.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.UserGroupEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Repository
@SuppressWarnings("all")
public interface UserGroupJpaRepository extends QueryDslRepository<UserGroupEntity, String> {

    boolean existsByName(String name);

    UserGroupEntity findByName(String name);

    @EntityGraph(value = "Group.users", type = EntityGraph.EntityGraphType.LOAD)
    List<UserGroupEntity> findAllByNameContaining(String name);

    @EntityGraph(value = "Group.users", type = EntityGraph.EntityGraphType.LOAD)
    List<UserGroupEntity> findAll();

    @Override
    @EntityGraph(value = "Group.users")
    Optional<UserGroupEntity> findById(@NonNull String id);

    void deleteAllByIdIn(List<String> ids);

    @Modifying
    @Transactional
    @Query(value = "UPDATE UserGroupEntity t SET t.enable = :enable WHERE t.id in :ids")
    void updateStatus(List<String> ids, boolean enable);

    boolean existsByNameAndIdNot(String name, String id);
}
