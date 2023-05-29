package tech.ibrave.metabucket.application.user.restful.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.UserMapper;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.application.user.restful.request.UpdateBulkUserReq;
import tech.ibrave.metabucket.application.user.restful.response.ResetPasswordResp;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserFacade {
    private final UserMapper userMapper;
    private final UserUseCase userUseCase;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_PASSWORD = "abcd@1234";

    public SuccessResponse createUser(PersistUserReq req) {
        validateNewUser(req);
        var passwordEncoded = passwordEncoder.encode(DEFAULT_PASSWORD);
        var user = userMapper.toUser(req, passwordEncoded);
        return new SuccessResponse(userUseCase.save(user).getId(),
                messageSource.getMessage("mb.users.create.success"));
    }

    public SuccessResponse updateUser(String userId, PersistUserReq req) {
        var user = userUseCase.getOrElseThrow(userId);
        userMapper.updateUser(user, req);
        var updatedUser = userUseCase.save(user);

        return new SuccessResponse(updatedUser.getId(),
                messageSource.getMessage("mb.users.update.success"));
    }

    public SuccessResponse updateBulkUser(UpdateBulkUserReq req) {
        userUseCase.updateStatusBulkUser(req.getUserIds(), req.isEnable());
        return SuccessResponse.ofMessage("mb.users.bulk-update.success");
    }

    public Page<UserDto> searchUser(SearchUserReq req) {
        return userUseCase.searchUser(req);
    }

    public UserDto getUser(String userId) {
        return userUseCase.findByIdUseDto(userId);
    }

    public ResetPasswordResp resetPassword(String userId) {
        // todo Check if use can edit

        var newPassword = RandomStringUtils.randomAlphabetic(8);
        var updatedUser = userUseCase.update(userId, updateUser -> updateUser.setPassword(passwordEncoder.encode(newPassword)));

        return new ResetPasswordResp(updatedUser.getId(),
                messageSource.getMessage("mb.users.reset_pw.success"),
                newPassword);
    }

    private void validateNewUser(PersistUserReq persistUserReq) {
        if (userUseCase.existByEmail(persistUserReq.getEmail())) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_EMAIL);
        }

        if (userUseCase.existByUsername(persistUserReq.getUsername())) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_USERNAME);
        }
    }
}
