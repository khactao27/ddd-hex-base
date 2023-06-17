package tech.ibrave.metabucket.infras.persistence.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.metadata.MultiValueMetadata;
import tech.ibrave.metabucket.domain.metadata.persistence.MultiValueMetadataPersistence;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MultiValueMetadataEntity;
import tech.ibrave.metabucket.infras.persistence.mapper.MultiValueMetadataEntityMapper;

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
