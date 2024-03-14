package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/organisationUsers")
public class OrganisationUsersController {

    @Autowired
    Keycloak keycloak;

    @Value("${keycloak.config.realm}")
    private String realm;

    /**
     * Get all users for an organisation
     * @param orgId the organisation ID
     * @return a list of user information who are in the organisation
     */
    @GetMapping("/{orgId}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    List<UserInfo> getUserOrganisations(@PathVariable String orgId) {
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().searchByAttributes("organisation:" + orgId);

            List<UserInfo> res = new ArrayList<>();

            for (var user : users) {
                res.add(new UserInfo(user.getId(), user.getFirstName(), user.getLastName()));
            }

            return res;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
