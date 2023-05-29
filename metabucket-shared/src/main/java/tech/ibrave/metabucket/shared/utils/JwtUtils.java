package tech.ibrave.metabucket.shared.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {

    private final String jwtIssuer;
    private final String jwtAudience;
    private final String jwtSecret;
    private final long jwtExpirationMinutes;

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

        byte[] signingKey = jwtSecret.getBytes();

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
        byte[] signingKey = jwtSecret.getBytes();

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

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        try {
            byte[] signingKey = jwtSecret.getBytes();

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