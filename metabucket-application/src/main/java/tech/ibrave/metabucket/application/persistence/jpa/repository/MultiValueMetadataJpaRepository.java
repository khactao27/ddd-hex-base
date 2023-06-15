package tech.ibrave.metabucket.application.persistence.jpa.repository;

import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.application.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MultiValueMetadataEntity;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Repository
@SuppressWarnings("all")
public interface MultiValueMetadataJpaRepository extends QueryDslRepository<MultiValueMetadataEntity, String> {
}
