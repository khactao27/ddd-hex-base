package tech.ibrave.metabucket.domain.metadata.persistence;

import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
public interface MetadataCategoryPersistence extends BasePersistence<MetadataCategory, Long> {
    Page<MetadataCategoryDto> search(SearchMetadataCategoryReq req);
    boolean existsByName(String name);
    List<Long> getAllChildrenId(Long parentId);
    List<MetadataCategoryDto> getAllChildren(Long parentId);
    List<MetadataCategoryDto> getAllRelation(Long id);
}
