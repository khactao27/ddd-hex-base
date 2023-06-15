package tech.ibrave.metabucket.application.metadata.restful.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.metadata.restful.mapper.MetadataDefinitionMapper;
import tech.ibrave.metabucket.application.metadata.restful.mapper.MultiValueMetadataMapper;
import tech.ibrave.metabucket.application.metadata.restful.request.MetadataDefinitionPersistenceReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.metadata.MetadataDefinition;
import tech.ibrave.metabucket.domain.metadata.ValueType;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataDefinitionAuditingObject;
import tech.ibrave.metabucket.domain.metadata.usecase.MetadataDefinitionUseCase;
import tech.ibrave.metabucket.domain.shared.request.MetadataDefinitionSearchReq;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Component
@RequiredArgsConstructor
public class MetadataDefinitionFacade {
    private final MetadataDefinitionUseCase definitionUseCase;
    private final MetadataDefinitionMapper definitionMapper;
    private final MultiValueMetadataMapper multiValueMetadataMapper;
    private final MessageSource messageSource;

    public SuccessResponse create(MetadataDefinitionPersistenceReq req) {
        validateExistsName(req.getName());
        var definition = definitionMapper.toDefinition(req);
        setMultiValue(definition, req);
        var id = definitionUseCase.save(definition).getId();
        return new SuccessResponse(id, messageSource.getMessage("mb.metadata.create.success"));
    }

    public SuccessResponse update(String id, MetadataDefinitionPersistenceReq req) {
        var definition = definitionUseCase.getOrElseThrow(id);
        if (!req.getName().equals(definition.getName())) {
            validateExistsName(req.getName());
        }
        definitionMapper.updateDefinition(definition, req);
        setMultiValue(definition, req);
        definitionUseCase.save(definition);
        return new SuccessResponse(id, messageSource.getMessage("mb.metadata.edit.success"));
    }

    public SuccessResponse delete(String id) {
        definitionUseCase.deleteIfExist(id);
        return new SuccessResponse(id, messageSource.getMessage("mb.Metadata.delete.success"));
    }

    public MetadataDefinitionAuditingObject getDetail(String id) {
        return definitionMapper.toDto(definitionUseCase.getOrElseThrow(id));
    }

    public Page<MetadataDefinitionAuditingObject> search(MetadataDefinitionSearchReq req) {
        return definitionUseCase.search(req);
    }

    public void setMultiValue(MetadataDefinition metadataDefinition, MetadataDefinitionPersistenceReq req) {
        if (Arrays.asList(ValueType.MULTI_SELECT_VALUE, ValueType.SINGLE_SELECT_VALUE).contains(req.getValueType())) {
            validateMultiValues(req.getMultiValues());

            metadataDefinition.setMultiValues(CollectionUtils
                    .toSet(req.getMultiValues(), multiValueMetadataMapper::toDomainModel));
        }
    }

    private void validateMultiValues(Set<MetadataDefinitionPersistenceReq.MultiValueReq> multiValues) {
        // Check if multi values duplicated
        var seen = new HashSet<String>(5);
        for (var multiValue : multiValues) {
            if (!seen.add(multiValue.getValue())) {
                throw new ErrorCodeException(ErrorCodes.DUPLICATE_MULTI_VALUE);
            }
        }
    }

    private void validateExistsName(String name) {
        if (definitionUseCase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.DUPLICATE_METADATA_NAME);
        }
    }
}
