package tech.ibrave.metabucket.infras.persistence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.metadata.MetadataOption;
import tech.ibrave.metabucket.domain.metadata.persistence.MetadataOptionPersistence;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.MetadataOptionEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.MetadataOptionJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.MetadataOptionEntityMapper;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Component
public class MetadataOptionAdapter
        extends BaseJpaRepository<MetadataOptionEntity, MetadataOption, Long>
        implements MetadataOptionPersistence {
    protected MetadataOptionAdapter(MetadataOptionJpaRepository repo,
                                    MetadataOptionEntityMapper mapper) {
        super(repo, mapper);
    }
}
