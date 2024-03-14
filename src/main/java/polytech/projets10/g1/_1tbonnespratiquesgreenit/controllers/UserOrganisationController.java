package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.ws.rs.NotFoundException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Organisation;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.OrganisationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userOrganisations")
public class UserOrganisationController {

    @Autowired
    Keycloak keycloak;

    @Value("${keycloak.config.realm}")
    private String realm;

    private final OrganisationRepository organisationRepository;

    public UserOrganisationController(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    /**
     * Get all the organisation to which a user belongs
     * @param userId the user id
     * @return a list of organisation the user belongs to
     */
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    List<Organisation> getUserOrganisations(@PathVariable String userId) {
        UserResource user = keycloak.realm(this.realm).users().get(userId);
        try {
            UserRepresentation userRepresentation = user.toRepresentation();
            Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

            if(existingAttributes.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no attributes");

            List<Organisation> res = new ArrayList<>();

            for(String orgId: existingAttributes.get("organisation")) {
                var orga = this.organisationRepository.findById(Long.parseLong(orgId));
                orga.ifPresent(res::add);
            }
            return res;

        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + userId, e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    /**
     * Remove a user from an organisation
     * @param userId the user ID
     * @param deleteOrganisationId the organisation
     */
    @PostMapping("/remove/{userId}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    void removeUserOrganisation(@PathVariable String userId, @RequestBody String deleteOrganisationId) {
        UserResource user = keycloak.realm(this.realm).users().get(userId);
        try {
            UserRepresentation userRepresentation = user.toRepresentation();
            Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

            if(existingAttributes.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no attributes");

            List<String> userOrganisations = existingAttributes.get("organisation");
            if (userOrganisations == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur n'appartient à aucune organisation");
            if (!userOrganisations.contains(deleteOrganisationId))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur n'appartient pas à cette organisation: " + deleteOrganisationId);

            userOrganisations.remove(deleteOrganisationId);

            existingAttributes.put("organisation", userOrganisations);

            user.update(userRepresentation);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + userId, e);
        }

    }

    /**
     * Add a user in an organisation
     * @param userId the user ID
     * @param newOrganisationId the organisation
     */
    @PostMapping("/add/{userId}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    void addUserOrganisations(@PathVariable String userId, @RequestBody String newOrganisationId) {
        UserResource user = keycloak.realm(this.realm).users().get(userId);
        try {
            UserRepresentation userRepresentation = user.toRepresentation();
            Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

            if(existingAttributes.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no attributes");

            List<String> userOrganisations = existingAttributes.get("organisation");

            if (userOrganisations == null) userOrganisations = new ArrayList<>();
            if (userOrganisations.contains(newOrganisationId))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur appartient déjà à cette organisation: " + newOrganisationId);

            userOrganisations.add(newOrganisationId);

            existingAttributes.put("organisation", userOrganisations);

            user.update(userRepresentation);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + userId, e);
        }

    }
}
