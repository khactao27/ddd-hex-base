package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.StorageEntity;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Repository
public interface StorageJpaRepository extends QueryDslRepository<StorageEntity, Integer> {
    boolean existsByName(String name);
}
