package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Player {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToMany
    Set<Organisation> playerOrganisations;
}
