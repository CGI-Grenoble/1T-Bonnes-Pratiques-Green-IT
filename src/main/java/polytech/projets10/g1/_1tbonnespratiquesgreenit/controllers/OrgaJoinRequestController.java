package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
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
    private final OrgaJoinRequestRepository orgaJoinRequestRepository;

    @Autowired
    Keycloak keycloak;

    @Value("${keycloak.config.realm}")
    private String realm;

    public OrgaJoinRequestController(OrgaJoinRequestRepository orgaJoinRequestRepository) {
        this.orgaJoinRequestRepository = orgaJoinRequestRepository;
    }

    /**
     * Get all join requests
     * @return all join requests
     */
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_org-admin')")
    public List<OrgaJoinRequest> getAllJoinRequests() {
        return orgaJoinRequestRepository.findAll();
    }

    /**
     * Get a join request
     * @param id the join request ID
     * @return the join request
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<OrgaJoinRequest> getOrgaJoinRidequest(@PathVariable Long id) {
        var joinRequest = orgaJoinRequestRepository.findById(id);
        if (joinRequest.isPresent())
            return new ResponseEntity<>(joinRequest.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Modify a join request
     * @param id the request ID
     * @param joinRequest the request modified
     * @return the request modified
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<OrgaJoinRequest> updateJoinRequest(@PathVariable Long id, @RequestBody @Valid OrgaJoinRequest joinRequest) {
        if (joinRequest.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: null");
        if (!Objects.equals(id, joinRequest.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: invalid");
        if (!orgaJoinRequestRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity not found");

        OrgaJoinRequest res = orgaJoinRequestRepository.save(joinRequest);
        return ResponseEntity.ok().body(res);
    }

    /**
     * Get all join requests for an organisation
     * @param orgaId the organisation ID
     * @return all the join requests for this organisation
     */
    @GetMapping("/forOrga/{orgaId}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public List<OrgaJoinRequest> getJoinRequestsForOrga(@PathVariable Long orgaId) {
        List<OrgaJoinRequest> requests = orgaJoinRequestRepository.findByOrga(orgaId);
        for (var request : requests) {
            UserRepresentation user = keycloak.realm(this.realm).users().get(request.getUser_id()).toRepresentation();
            System.out.println(user.getFirstName());
            System.out.println(request.getUser_id());
            UserInfo info = new UserInfo(request.getUser_id(), user.getFirstName(), user.getLastName());
            request.setUserInfo(info);
        }

        return requests;
    }

    /**
     * Accept of decline a request from a user
     * @param requestId the request id
     * @param decision the decision either "accept" or "decline"
     */
    @PostMapping("/{requestId}/decide")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public void decideJoinRequest(@PathVariable Long requestId, @RequestBody String decision) {
        var joinRequest = orgaJoinRequestRepository.findById(requestId);
        if (!joinRequest.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This request does not exist: " + requestId);

        OrgaJoinRequest request = joinRequest.get();
        String requestedOrganisation = String.valueOf(request.getOrganisation().getId());

        if (!decision.equalsIgnoreCase(acceptRequest) && !decision.equalsIgnoreCase(declineRequest))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Décision non reconnue: " + decision);

        if (decision.equalsIgnoreCase(acceptRequest)) {
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

    /**
     * Create an organisation join request
     * @param joinRequest the join request
     * @return the join request
     * @throws BadRequestException the request already has an ID
     * @throws URISyntaxException
     */
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

            for (var userRequest : userRequests) {
                if (Objects.equals(userRequest.getOrganisation().getId(), joinRequest.getOrganisation().getId()))
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

    /**
     * Delete a join request
     * @param id the join request ID
     * @return a success response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<Void> deleteJoinRequest(@PathVariable Long id) {
        orgaJoinRequestRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
