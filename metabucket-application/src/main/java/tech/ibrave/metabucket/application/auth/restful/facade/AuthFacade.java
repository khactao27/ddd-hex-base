package tech.ibrave.metabucket.application.auth.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.auth.base.UserAuthentication;
import tech.ibrave.metabucket.application.auth.base.AuthErrorCodes;
import tech.ibrave.metabucket.application.auth.base.UserRepoDetails;
import tech.ibrave.metabucket.application.auth.restful.mapper.PrincipalMapper;
import tech.ibrave.metabucket.application.auth.restful.request.LoginReq;
import tech.ibrave.metabucket.application.auth.restful.response.LoginSuccessResp;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.utils.JwtUtils;

/**
 * Author: anct
 * Date: 29/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final JwtUtils jwtUtils;
    private final PrincipalMapper mapper;
    private final AuthenticationManager authenticationManager;

    public LoginSuccessResp login(LoginReq req) {
        if (req.getSource() == UserSource.SELF_REGISTER) {
            try {
                var basicAuth = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
                var authentication =  authenticationManager.authenticate(basicAuth);
                return map(authentication);
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
                throw new ErrorCodeException(AuthErrorCodes.INVALID_USERNAME_OR_PW);
            }
        }

        return null;
    }

    private LoginSuccessResp map(Authentication authentication) {
        var userDetails = (UserRepoDetails) authentication.getPrincipal();
        return new LoginSuccessResp(jwtUtils.generate(authentication), mapper.toDto(userDetails.getUser()));
    }
}
