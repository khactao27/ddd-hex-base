package tech.ibrave.metabucket.application.persistence.adapter;

import io.micrometer.common.util.StringUtils;
import tech.ibrave.metabucket.application.metadata.restful.mapper.MetadataCategoryMapper;
import tech.ibrave.metabucket.application.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.MetadataCategoryEntity;
import tech.ibrave.metabucket.application.persistence.jpa.repository.MetadataCategoryJpaRepository;
import tech.ibrave.metabucket.application.persistence.mapper.MetadataCategoryEntityMapper;
import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.metadata.persistence.MetadataCategoryPersistence;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
public class MetadataCategoryAdapter
        extends BaseJpaRepository<MetadataCategoryEntity, MetadataCategory, String>
        implements MetadataCategoryPersistence {
    protected MetadataCategoryAdapter(MetadataCategoryJpaRepository repo,
                                      MetadataCategoryEntityMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public Page<MetadataCategoryDto> search(SearchMetadataCategoryReq req) {
        var pageable = getPageable(req);

        var page = StringUtils.isEmpty(req.getName())
                ? repo().findAll(pageable)
                : repo().findAllByNameContaining(req.getName(), pageable);

        return toPage(page.getContent(), mapper()::toDto, pageable);
    }

    @Override
    public MetadataCategoryMapper mapper() {
        return super.mapper();
    }

    @Override
    public MetadataCategoryJpaRepository repo() {
        return super.repo();
    }
}
