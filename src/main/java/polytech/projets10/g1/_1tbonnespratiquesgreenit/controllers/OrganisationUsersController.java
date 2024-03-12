package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET}
)
@RequestMapping("/api/organisationUsers")
public class OrganisationUsersController {

    @Autowired
    Keycloak keycloak;

    @GetMapping("/{orgaName}")
    List<UserInfo> getUserOrganisations(@PathVariable String orgaName) {
        List<UserRepresentation> users = keycloak.realm("1t-bonnes-pratiques").users().searchByAttributes("organisation:" + orgaName);

        List<UserInfo> res = new ArrayList<>();

        for (var user : users) {
            res.add(new UserInfo(user.getId(), user.getFirstName(), user.getLastName()));
        }

        return res;
    }
}
