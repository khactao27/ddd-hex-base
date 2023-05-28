package tech.ibrave.metabucket.infra.peristence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.UserEntity;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Repository
@SuppressWarnings("all")
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
