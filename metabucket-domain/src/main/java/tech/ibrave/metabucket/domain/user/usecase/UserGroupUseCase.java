package tech.ibrave.metabucket.domain.user.usecase;

import tech.ibrave.metabucket.domain.shared.request.UserGroupSearchReq;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
import java.util.Optional;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
public interface UserGroupUseCase extends BaseUseCase<UserGroup, String> {

    Page<UserGroupDto> search(UserGroupSearchReq req);

    UserGroupDto findUserGroupDtoById(String id);

    void deleteByIds(List<String> ids);

    void updateStatus(List<String> ids, boolean status);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, String id);
}
