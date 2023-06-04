package tech.ibrave.metabucket.integration.google;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tech.ibrave.metabucket.integration.oauth.OAuth2Service;
import tech.ibrave.metabucket.integration.oauth.OAuth2TokenResp;

/**
 * @author an.cantuong
 */
@Component
public class GoogleOAuth2Service extends OAuth2Service {

    protected GoogleOAuth2Service(GoogleOAuthConfig config, RestTemplate restTemplate) {
        super(config, restTemplate);
    }

    /**
     * Get token from oauth server
     */
    @Override
    protected OAuth2TokenResp getOAuth2Token(String grantedCode) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(oAuthConfig.buildAccessTokenReq(grantedCode), headers);
        return restTemplate.postForObject(oAuthConfig.getTokenUrl(), request, OAuth2TokenResp.class);
    }
}

