package tech.ibrave.metabucket.infras.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.ibrave.metabucket.application.auth.base.UserAuthentication;
import tech.ibrave.metabucket.application.auth.base.UserRepoDetails;
import tech.ibrave.metabucket.shared.utils.JwtUtils;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        try {
            getJwtFromRequest(request)
                    .flatMap(jwtUtils::validateTokenAndGetJws)
                    .ifPresent(jws -> {
                        var username = jws.getBody().getSubject();
                        // set user details on spring security context
                        var userDetails = (UserRepoDetails) userService.loadUserByUsername(username);

                        var userAuthentication = new UserAuthentication(userDetails.getUser(), userDetails.getAuthorities());
                        userAuthentication.setAuthenticated(true);
                        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                    });
        } catch (Exception e) {
            log.error("Cannot set user authentication", e);
        }

        chain.doFilter(request, response);
    }

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {
        var tokenHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(tokenHeader) && tokenHeader.startsWith("Bearer ")) {
            return Optional.of(tokenHeader.replace("Bearer ", ""));
        }

        return Optional.ofNullable(request.getParameter("token"));
    }
}