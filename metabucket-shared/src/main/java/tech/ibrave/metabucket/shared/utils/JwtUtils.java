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

import java.time.ZonedDateTime;
import java.util.Date;
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
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String generateWithExpiredTime(String username, Date expiredDate) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(expiredDate)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(username)
                .compact();
    }

    @SneakyThrows
    public String generateRegisterUserJwt(String email) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam(TARGET, JwtTarget.USER_REGISTRATION)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .claim("email", email)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(email)
                .compact();
    }

    @SneakyThrows
    public String generateForgotPasswordJwt(String email) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam(TARGET, JwtTarget.FORGOT_PASSWORD)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .claim("email", email)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(email)
                .compact();
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