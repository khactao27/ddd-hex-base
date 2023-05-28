package tech.ibrave.metabucket.domain.user.persistence;

import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.Optional;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RolePersistence extends BasePersistence<Role, Long> {

    boolean existsByName(String name);

    Page<Role> search(String name,
                      int pageIndex,
                      int pageSize);

    Page<Role> findAllByName(String name, int pageIndex, int pageSize);

    Optional<RoleDto> findByIdUseDto(Long id);

    void deleteByIds(List<Long> ids);

    void updateStatus(List<Long> ids, boolean status);
}
