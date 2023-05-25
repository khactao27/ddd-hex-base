package tech.ibrave.metabucket.infra.peristence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.RoleEntity;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    boolean existsByName(String name);

    RoleEntity findByName(String name);

    List<RoleEntity> findAllByNameContaining(String name);
}
