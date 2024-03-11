package polytech.projets10.g1._1tbonnespratiquesgreenit.keycloak;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.Keycloak;

@Service
public class AdminClientService {

    @Autowired
    Keycloak keycloak;

    @PostConstruct
    void searchUsers() {
        // ...
    }
}
