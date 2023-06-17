package tech.ibrave.metabucket.infras.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tech.ibrave.metabucket.infras.filters.AuthenticationFilter;
import tech.ibrave.metabucket.infras.persistence.jpa.auth.UserJpaDetailsService;
import tech.ibrave.metabucket.shared.utils.JwtUtils;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.cors.enabled}")
    private boolean corsEnabled;

    @Value("${security.permit-all-for-dev}")
    private boolean permitAllForDev;

    @Value("${security.jwt.issuer}")
    public String jwtIssuer;

    @Value("${security.jwt.audience}")
    public String jwtAudience;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expire-in-minutes}")
    private long jwtExpirationMinutes;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (permitAllForDev) {
            http.authorizeHttpRequests(registry -> registry.anyRequest().permitAll());
        } else {
            http.authorizeHttpRequests(registry -> registry.requestMatchers("/",
                            "/v1/public/**",
                            "/v1/auth/**",
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/v3/api-docs",
                            "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated());
        }

        if (corsEnabled) http.cors(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        // Set exception handling
        http.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());

        // add authentication filter
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserJpaDetailsService();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(jwtIssuer, jwtAudience, jwtSecret, jwtExpirationMinutes);
    }

    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter(jwtUtils(), userDetailsService());
    }
}