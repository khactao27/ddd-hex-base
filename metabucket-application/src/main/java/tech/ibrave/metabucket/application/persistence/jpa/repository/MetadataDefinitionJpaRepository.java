package tech.ibrave.metabucket.application.persistence.jpa.repository;

import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.application.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataDefinitionEntity;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Repository
public interface MetadataDefinitionJpaRepository extends QueryDslRepository<MetadataDefinitionEntity, String> {
    boolean existsByName(String name);
}
