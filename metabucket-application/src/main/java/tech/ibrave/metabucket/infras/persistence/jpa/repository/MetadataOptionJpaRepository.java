package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataOptionEntity;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Repository
@SuppressWarnings("all")
public interface MetadataOptionJpaRepository extends QueryDslRepository<MetadataOptionEntity, Long> {
}
