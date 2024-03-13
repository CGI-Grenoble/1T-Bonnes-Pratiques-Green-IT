package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    Keycloak keycloak;

    @Value("${keycloak.config.realm}")
    private String realm;

    /**
     * Get user information (name)
     * @param id the user id
     * @return user first name and last name
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public UserInfo getCard(@PathVariable String id) {
        UserRepresentation user = keycloak.realm(this.realm).users().get(id).toRepresentation();

        return new UserInfo(id, user.getFirstName(), user.getLastName());
    }
}
