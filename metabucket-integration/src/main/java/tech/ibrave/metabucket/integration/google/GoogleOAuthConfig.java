package tech.ibrave.metabucket.integration.google;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.integration.oauth.OAuthConfig;

/**
 * Author: hungnm
 * Date: 2/9/2023
 * #YWNA
 */
@Component
@ConfigurationProperties(prefix = "integration.google")
public class GoogleOAuthConfig extends OAuthConfig {
}
