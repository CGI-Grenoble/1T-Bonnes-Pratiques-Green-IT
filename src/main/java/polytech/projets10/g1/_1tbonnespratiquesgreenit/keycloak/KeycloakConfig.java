package polytech.projets10.g1._1tbonnespratiquesgreenit.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.config.serverUrl}")
    private String serverUrl;

    @Value("${keycloak.config.realm}")
    private String realm;

    @Value("${keycloak.config.clientId}")
    private String clientId;

    @Value("${keycloak.config.username}")
    private String username;

    @Value("${keycloak.config.password}")
    private String password;

    @Bean
    public Keycloak keycloak() {

        return KeycloakBuilder.builder()
                .serverUrl(this.serverUrl)
                .realm(this.realm)
                .clientId(this.clientId)
                .grantType(OAuth2Constants.PASSWORD)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
