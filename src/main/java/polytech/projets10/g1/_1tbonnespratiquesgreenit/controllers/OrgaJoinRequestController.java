package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import org.apache.coyote.BadRequestException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Card;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.OrgaJoinRequest;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.OrgaJoinRequestRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/orgaJoinRequests")

public class OrgaJoinRequestController {

    private static final String acceptRequest = "ACCEPT";
    private static final String declineRequest = "DECLINE";

    @Autowired
    Keycloak keycloak;

    @Value("${keycloak.config.realm}")
    private String realm;

    private OrgaJoinRequestRepository orgaJoinRequestRepository;

    public OrgaJoinRequestController(OrgaJoinRequestRepository orgaJoinRequestRepository) {
        this.orgaJoinRequestRepository = orgaJoinRequestRepository;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_org-admin')")
    public List<OrgaJoinRequest> getAllJoinRequests() {
        return orgaJoinRequestRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<OrgaJoinRequest> getOrgaJoinRequest(@PathVariable Long id) {
        var joinRequest = orgaJoinRequestRepository.findById(id);
        if (joinRequest.isPresent())
            return new ResponseEntity<>(joinRequest.get(), HttpStatus.OK);
        return new ResponseEntity<>( null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/forOrga/{orgaId}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public List<OrgaJoinRequest> getJoinRequestsForOrga(@PathVariable Long orgaId) {
        return orgaJoinRequestRepository.findByOrga(orgaId);
    }

    @PostMapping("/{requestId}/decide")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public void decideJoinRequest(@PathVariable Long requestId, @RequestBody String decision) {
        var joinRequest = orgaJoinRequestRepository.findById(requestId);
        if (!joinRequest.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This request does not exist: " + requestId);

        OrgaJoinRequest request = joinRequest.get();
        String requestedOrganisation = String.valueOf(request.getOrganisation().getId());

        if(!decision.equalsIgnoreCase(acceptRequest) && !decision.equalsIgnoreCase(declineRequest))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Décision non reconnue: " + decision);

        if(decision.equalsIgnoreCase(acceptRequest)) {
            UserResource user = keycloak.realm(this.realm).users().get(request.getUser_id());
            try {
                UserRepresentation userRepresentation = user.toRepresentation();
                Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

                List<String> userOrganisations = existingAttributes.get("organisation");

                if (userOrganisations == null) userOrganisations = new ArrayList<>();
                if (userOrganisations.contains(requestedOrganisation))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur appartient déjà à cette organisation: " + requestedOrganisation);

                userOrganisations.add(requestedOrganisation);

                existingAttributes.put("organisation", userOrganisations);

                user.update(userRepresentation);
            } catch (NotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + request.getUser_id(), e);
            }
        }

        this.orgaJoinRequestRepository.delete(request);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<OrgaJoinRequest> createOrgaJoinRequest(@RequestBody OrgaJoinRequest joinRequest) throws BadRequestException, URISyntaxException {
        if (joinRequest.getId() != null)
            throw new BadRequestException("A new join request cannot already have an ID");
        UserResource user = keycloak.realm(this.realm).users().get(joinRequest.getUser_id());
        String requestedOrganisation = String.valueOf(joinRequest.getOrganisation().getId());
        try {
            UserRepresentation userRepresentation = user.toRepresentation();
            Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

            List<String> userOrganisations = existingAttributes.get("organisation");

            if (userOrganisations != null && userOrganisations.contains(requestedOrganisation))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur appartient déjà à cette organisation: " + requestedOrganisation);


            List<OrgaJoinRequest> userRequests = orgaJoinRequestRepository.findByUserId(joinRequest.getUser_id());

            for(var userRequest: userRequests) {
                if(Objects.equals(userRequest.getOrganisation().getId(), joinRequest.getOrganisation().getId()))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur a déjà fait une demande pour cette organisation: " + requestedOrganisation);
            }

        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + joinRequest.getUser_id(), e);
        }
        var result = orgaJoinRequestRepository.save(joinRequest);
        return ResponseEntity
                .created(new URI("/api/orgaJoinRequests/" + result.getId()))
                .body(result);
    }


}