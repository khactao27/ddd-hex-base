package tech.ibrave.metabucket.domain.user.persistence;

import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.model.request.PageReq;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RolePersistence extends BasePersistence<Role, Long> {

    boolean existsByName(String name);
    Page<RoleLiteDto> search(String name, PageReq pageReq);
    Optional<RoleDto> findByIdUseDto(Long id);
    void deleteByIds(List<Long> ids);
    void updateStatus(List<Long> ids, boolean status);
}
