package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Organisation;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.OrganisationRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationRepository organisationRepository;

    public OrganisationController(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Organisation> getOrganisation(@PathVariable Long id) {
        var organisation = organisationRepository.findById(id);
        if (organisation.isPresent())
            return new ResponseEntity<>(organisation.get(), HttpStatus.OK);
        return new ResponseEntity<>((Organisation) null, HttpStatus.NOT_FOUND);
    }

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
}
