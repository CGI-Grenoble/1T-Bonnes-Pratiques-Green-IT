package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.ws.rs.NotFoundException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST}
)
@RequestMapping("/api/userOrganisations")
public class UserOrganisationController {

    @Autowired
    Keycloak keycloak;

    @GetMapping("/{userId}")
    List<String> getUserOrganisations(@PathVariable String userId) {
        UserResource user;
        try {
            user = keycloak.realm("1t-bonnes-pratiques").users().get(userId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas", e);
        }
        UserRepresentation userRepresentation = user.toRepresentation();
        Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

        return existingAttributes.get("organisation");
    }

    @PostMapping("/remove/{userId}")
    void removeUserOrganisation(@PathVariable String userId, @RequestBody String deleteOrganisation) {
        UserResource user;
        try {
            user = keycloak.realm("1t-bonnes-pratiques").users().get(userId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas", e);
        }
        UserRepresentation userRepresentation = user.toRepresentation();
        Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

        List<String> userOrganisations = existingAttributes.get("organisation");
        if (userOrganisations == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur n'appartient à aucune organisation");
        if (!userOrganisations.contains(deleteOrganisation))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur n'appartient pas à cette organisation: " + deleteOrganisation);

        userOrganisations.remove(deleteOrganisation);

        existingAttributes.put("organisation", userOrganisations);

        user.update(userRepresentation);
    }

    @PostMapping("/add/{userId}")
    void addUserOrganisations(@PathVariable String userId, @RequestBody String newOrganisation) {
        UserResource user;
        try {
            user = keycloak.realm("1t-bonnes-pratiques").users().get(userId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas", e);
        }
        UserRepresentation userRepresentation = user.toRepresentation();
        Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

        List<String> userOrganisations = existingAttributes.get("organisation");

        if (userOrganisations == null)
            userOrganisations = new ArrayList<>();
        if (userOrganisations.contains(newOrganisation))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur appartient déjà à cette organisation: " + newOrganisation);

        userOrganisations.add(newOrganisation);

        existingAttributes.put("organisation", userOrganisations);

        user.update(userRepresentation);
    }
}
