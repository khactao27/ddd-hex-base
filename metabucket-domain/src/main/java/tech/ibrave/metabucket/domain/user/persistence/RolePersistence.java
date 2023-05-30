package tech.ibrave.metabucket.domain.user.persistence;

import org.springframework.data.domain.Pageable;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RolePersistence extends BasePersistence<Role, Long> {

    boolean existsByName(String name);

    Page<Role> search(String name, Pageable pageable);

    Page<Role> findAllByName(String name, Pageable pageable);

    Optional<RoleDto> findByIdUseDto(Long id);

    void deleteByIds(List<Long> ids);

    void updateStatus(List<Long> ids, boolean status);
}
