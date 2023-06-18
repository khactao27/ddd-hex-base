package tech.ibrave.metabucket.application.metadata.restful.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.metadata.restful.mapper.MetadataCategoryMapper;
import tech.ibrave.metabucket.application.metadata.restful.request.DeleteMetadataCategoryReq;
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
        if (req.getParentId() != null) {
            validateExistsById(req.getParentId());
        }
        validateExistsByName(req.getName());
        var category = categoryMapper.toDomain(req);
        var id = categoryUseCase.save(category).getId();
        return new SuccessResponse(id, messageSource.getMessage("mb.categories.create.success"));
    }

    public SuccessResponse update(Long id, MetadataCategoryPersistenceReq req) {
        var category = categoryUseCase.getOrElseThrow(id);
        if (req.getParentId() != null) {
            validateExistsById(req.getParentId());
        }
        if (!category.getName().equals(req.getName())) {
            validateExistsByName(req.getName());
        }
        categoryMapper.updateCategory(category, req);
        return new SuccessResponse(id, messageSource.getMessage("mb.categories.edit.success"));
    }

    public Page<MetadataCategoryDto> search(SearchMetadataCategoryReq req) {
        return categoryUseCase.search(req);
    }

    public SuccessResponse delete(DeleteMetadataCategoryReq req) {
        categoryUseCase.deleteAllByIdInBatch(req.getIds());
        return SuccessResponse.ofMessageCode("mb.categories.delete.success");
    }

    public MetadataCategoryDto getDetail(Long id) {
        var category = categoryUseCase.getOrElseThrow(id);
        return categoryMapper.toDto(category);
    }

    public void validateExistsById(Long id) {
        if (!categoryUseCase.existById(id)) {
            throw new ErrorCodeException(ErrorCodes.NOT_FOUND);
        }
    }

    public void validateExistsByName(String name) {
        if (categoryUseCase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.DUPLICATE_CATEGORY_NAME);
        }
    }
}
