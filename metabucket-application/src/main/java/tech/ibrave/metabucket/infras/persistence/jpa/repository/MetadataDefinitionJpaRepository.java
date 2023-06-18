package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataDefinitionEntity;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Repository
@SuppressWarnings("all")
public interface MetadataDefinitionJpaRepository extends QueryDslRepository<MetadataDefinitionEntity, Long> {
    boolean existsByName(String name);
}
