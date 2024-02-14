package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Organisation;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.OrganisationRepository;

import java.util.List;

@RestController
public class OrganisationController {

    private final OrganisationRepository organisationRepository;

    public OrganisationController(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @GetMapping("/organisations")
    public List<Organisation> getOrganisations() {
        return organisationRepository.findAll();
    }

    @PostMapping("/organisations")
    void addOrganisation(@RequestBody Organisation organisation) {
        organisationRepository.save(organisation);
    }
}
