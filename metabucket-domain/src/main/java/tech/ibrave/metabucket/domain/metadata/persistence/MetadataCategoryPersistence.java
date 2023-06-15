package tech.ibrave.metabucket.domain.metadata.persistence;

import tech.ibrave.metabucket.domain.metadata.MetadataCategory;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
public interface MetadataCategoryPersistence extends BasePersistence<MetadataCategory, String> {
    Page<MetadataCategoryDto> search(SearchMetadataCategoryReq req);
}
