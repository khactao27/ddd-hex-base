package tech.ibrave.metabucket.shared.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import tech.ibrave.metabucket.shared.constant.JwtTarget;

import java.lang.annotation.Target;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {

    private final String jwtIssuer;
    private final String jwtAudience;
    private final String jwtSecret;
    private byte[] signingKey;
    private final long jwtExpirationMinutes;
    public static final String TARGET = "target";

    @PostConstruct
    private void initKey() {
        signingKey = jwtSecret.getBytes();
    }

    public JwtUtils(String jwtIssuer,
                    String jwtAudience,
                    String jwtSecret,
                    long jwtExpirationMinutes) {
        this.jwtIssuer = jwtIssuer;
        this.jwtAudience = jwtAudience;
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMinutes = jwtExpirationMinutes;
    }

    public String generate(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        return generateAuthenticationJwt(userDetails.getUsername());
    }

    public String generateAuthenticationJwt(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam(TARGET, JwtTarget.AUTHENTICATE)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(username)
                .compact();
    }

    public String generateVerify2FAJwt(String userId, long jwtExpirationMinutes) {
        return generateWith(userId,
                Map.of("userId", userId),
                Map.of(TARGET, JwtTarget.VERIFY_2FA));
    }

    public String generateRegisterUserJwt(String email) {
        return generateWithEmail(email, JwtTarget.USER_REGISTRATION);
    }

    public String generateForgotPasswordJwt(String email) {
        return generateWithEmail(email, JwtTarget.FORGOT_PASSWORD);
    }

    public String generateWithEmail(String email, JwtTarget target) {
        return generateWith(email, Map.of("email", email), Map.of(TARGET, target));
    }

    public String generateWith(String subject,
                               Map<String, Object> claims,
                               Map<String, Object> headers,
                               long jwtExpirationMinutes) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParams(headers)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .addClaims(claims)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(subject)
                .compact();
    }

    public String generateWith(String subject,
                               Map<String, Object> claims,
                               Map<String, Object> headers) {
        return generateWith(subject, claims, headers, jwtExpirationMinutes);
    }

    public String generateWith(String subject, Map<String, Object> claims) {
        return generateWith(subject, claims, null);
    }

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);

            return Optional.of(jws);
        } catch (Exception e) {
            log.error("Request to parse expired JWT : {} failed : {}", token, e.getMessage());
        }
        return Optional.empty();
    }
}