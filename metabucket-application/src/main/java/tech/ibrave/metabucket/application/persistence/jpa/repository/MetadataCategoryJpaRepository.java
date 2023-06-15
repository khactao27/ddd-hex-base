package tech.ibrave.metabucket.application.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.application.persistence.jpa.QueryDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataCategoryEntity;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Repository
public interface MetadataCategoryJpaRepository extends QueryDslRepository<MetadataCategoryEntity, String> {
    Page<MetadataCategoryEntity> findAllByNameContaining(String name, Pageable pageable);
    boolean existsByName(String name);
}
