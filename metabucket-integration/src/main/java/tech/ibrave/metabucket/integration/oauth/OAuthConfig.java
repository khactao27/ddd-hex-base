package tech.ibrave.metabucket.integration.oauth;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: hungnm
 * Date: 2/9/2023
 * #YWNA
 */
@Getter
@Setter
public class OAuthConfig {
    public static final String GRANT_TYPE = "grant_type";
    public static final String CODE = "code";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String REFRESH_TOKEN = "refresh_token";
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String tokenUrl;
    private String userInfoUrl;
    private String refreshTokenUrl;

    public Map<String, String> buildAccessTokenReq(String code) {
        var param = new HashMap<String, String>();
        param.put(CODE, code);
        param.put(GRANT_TYPE, AUTHORIZATION_CODE);
        param.put(CLIENT_ID, clientId);
        param.put(CLIENT_SECRET, clientSecret);
        param.put(REDIRECT_URI, redirectUri);

        return param;
    }

    public Map<String, String> buildRefreshTokenReq(String refreshToken) {
        var params = new HashMap<String, String>();
        params.put(GRANT_TYPE, REFRESH_TOKEN);
        params.put(CLIENT_SECRET, clientSecret);
        params.put(CLIENT_ID, clientId);
        params.put(REFRESH_TOKEN, refreshToken);
        return params;
    }

}
