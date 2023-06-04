package tech.ibrave.metabucket.domain.user.usecase;

import org.springframework.data.domain.Pageable;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RoleUseCase extends BaseUseCase<Role, Long> {

    boolean existsByName(String name);
    Page<RoleDto> search(String name, Pageable pageable);
    RoleDto findByIdUseDto(Long id);
    void deleteByIds(List<Long> ids);
    void updateStatus(List<Long> ids, boolean status);
    boolean existsByNameAndIdNot(String name, Long id);
}
