package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Organisation;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.OrganisationRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationRepository organisationRepository;

    public OrganisationController(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    /**
     * Get all organisations
     * @return all the organisations
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    /**
     * Get an organisation
     * @param id the organisation ID
     * @return the organisation
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Organisation> getOrganisation(@PathVariable Long id) {
        var organisation = organisationRepository.findById(id);
        if (organisation.isPresent())
            return new ResponseEntity<>(organisation.get(), HttpStatus.OK);
        return new ResponseEntity<>((Organisation) null, HttpStatus.NOT_FOUND);
    }

    /**
     * Modify an organisation
     * @param id the organisation ID
     * @param organisation the organisation modified
     * @return the organisation modified
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable Long id, @RequestBody @Valid Organisation organisation) {
        if (organisation.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: null");
        if (!Objects.equals(id, organisation.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: invalid");
        if (!organisationRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity not found");

        Organisation res = organisationRepository.save(organisation);
        return ResponseEntity.ok().body(res);
    }

    /**
     * Create an organisation
     * @param organisation the organisation
     * @return the organisation created
     * @throws BadRequestException the organisation already has an ID
     * @throws URISyntaxException
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<Organisation> createOrganisation(@RequestBody Organisation organisation) throws BadRequestException, URISyntaxException {
        if (organisation.getId() != null)
            throw new BadRequestException("A new organisation cannot already have an ID");
        var result = organisationRepository.save(organisation);
        return ResponseEntity
                .created(new URI("/api/organisations/" + result.getId()))
                .body(result);
    }

    /**
     * Delete an organisation
     * @param id the organisation ID
     * @return a success response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        organisationRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
