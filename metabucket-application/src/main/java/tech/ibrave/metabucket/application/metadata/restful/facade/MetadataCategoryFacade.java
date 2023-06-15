package tech.ibrave.metabucket.application.metadata.restful.facade;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.metadata.restful.mapper.MetadataCategoryMapper;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataCategoryPersistenceReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryDto;
import tech.ibrave.metabucket.domain.metadata.usecase.MetadataCategoryUseCase;
import tech.ibrave.metabucket.domain.shared.request.SearchMetadataCategoryReq;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Component
@RequiredArgsConstructor
public class MetadataCategoryFacade {
    private final MetadataCategoryUseCase categoryUseCase;
    private final MetadataCategoryMapper categoryMapper;
    private final MessageSource messageSource;

    public SuccessResponse create(MetadataCategoryPersistenceReq req) {
        if (StringUtils.isNotEmpty(req.getParentId())) {
            validateExists(req.getParentId());
        }
        var category = categoryMapper.toDomain(req);
        var id = categoryUseCase.save(category).getId();
        return new SuccessResponse(id, messageSource.getMessage("mb.categories.create.success"));
    }

    public Page<MetadataCategoryDto> search(SearchMetadataCategoryReq req) {
        return categoryUseCase.search(req);
    }

    public void validateExists(String id) {
        if (!categoryUseCase.existById(id)) {
            throw new ErrorCodeException(ErrorCodes.NOT_FOUND);
        }
    }
}
