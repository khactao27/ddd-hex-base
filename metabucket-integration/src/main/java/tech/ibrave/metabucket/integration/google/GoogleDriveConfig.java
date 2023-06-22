package tech.ibrave.metabucket.integration.google;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Component
public class GoogleDriveConfig {
    private static final String APPLICATION_NAME = "Meta-bucket";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Value("${integration.google.client-id}")
    private String clientId;
    @Value("${integration.google.secret-id}")
    private String secretId;
    @Value("${integration.google.token-server}")
    private String tokenServer;
    public Drive getInstance(String accessToken, String refreshToken) throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final var HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, createCredential(accessToken, refreshToken))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public Credential createCredential(String accessToken, String refreshToken) throws GeneralSecurityException, IOException {
        final var HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        return new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setClientAuthentication(
                        new ClientParametersAuthentication(clientId, secretId))
                .setTokenServerEncodedUrl(tokenServer)
                .build()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken);
    }

}
