package tech.ibrave.metabucket.integration.oauth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Author: hungnm
 * Date: 2/9/2023
 * #YWNA
 */
public abstract class OAuth2Service {
    protected final OAuthConfig oAuthConfig;
    protected final RestTemplate restTemplate;

    protected OAuth2Service(OAuthConfig oAuthConfig,
                            RestTemplate restTemplate) {
        this.oAuthConfig = oAuthConfig;
        this.restTemplate = restTemplate;
    }

    /**
     * Get token from oauth server
     */
    protected abstract OAuth2TokenResp getOAuth2Token(String grantedCode);

    /**
     * Renew access token when old token has been expired
     */
    public OAuth2TokenResp refreshToken(String refreshToken) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(oAuthConfig.buildRefreshTokenReq(refreshToken), headers);
        return restTemplate.postForObject(oAuthConfig.getRefreshTokenUrl(), request, OAuth2TokenResp.class);
    }

    /**
     * get user info from oauth server
     */
    public <T> T getUserInfo(String accessToken, Class<T> clazz) {
        var uri = UriComponentsBuilder
                .fromHttpUrl(oAuthConfig.getUserInfoUrl())
                .queryParam("access_token", accessToken)
                .build();
        return restTemplate.getForObject(uri.toUri(), clazz);
    }
}

