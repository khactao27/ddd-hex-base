package tech.ibrave.metabucket.application.user.restful.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.UserMapper;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.application.user.restful.response.ResetPasswordResp;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
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

    public SuccessResponse createUser(PersistUserReq req) {
        var user = userMapper.toUser(req);
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

    public ResetPasswordResp resetPassword(String userId) {
        // todo Check if use can edit

        var newPassword = RandomStringUtils.randomAlphabetic(8);
        var updatedUser = userUseCase.update(userId, updateUser -> updateUser.setPassword(newPassword));

        return new ResetPasswordResp(updatedUser.getId(),
                messageSource.getMessage("mb.users.reset_pw.success"),
                newPassword);
    }
}
