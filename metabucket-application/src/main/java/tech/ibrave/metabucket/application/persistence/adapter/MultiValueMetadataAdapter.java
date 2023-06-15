package tech.ibrave.metabucket.application.persistence.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MultiValueMetadataEntity;
import tech.ibrave.metabucket.application.persistence.mapper.MultiValueMetadataEntityMapper;
import tech.ibrave.metabucket.domain.metadata.MultiValueMetadata;
import tech.ibrave.metabucket.domain.metadata.persistence.MultiValueMetadataPersistence;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Component
public class MultiValueMetadataAdapter
        extends BaseJpaRepository<MultiValueMetadataEntity, MultiValueMetadata, String>
        implements MultiValueMetadataPersistence {
    protected MultiValueMetadataAdapter(JpaRepository<MultiValueMetadataEntity, String> repo,
                                        MultiValueMetadataEntityMapper mapper) {
        super(repo, mapper);
    }
}
