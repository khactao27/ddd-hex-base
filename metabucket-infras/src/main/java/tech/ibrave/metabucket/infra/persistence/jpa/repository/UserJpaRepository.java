package tech.ibrave.metabucket.infra.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.UserEntity;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Repository
@SuppressWarnings("all")
public interface UserJpaRepository extends DslRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsAllByIdIn(List<String> ids);
    List<UserEntity> findByIdIn(List<String> ids);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity SET enable = :enable where id in :userIds")
    void updateStatusBulkUser(List<String> userIds, boolean enable);
}
