package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * An organisation regrouping users from a same company
 * A group of users from where games can be created.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the organisation
     */
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    /**
     * Short text for additional information
     */
    @Column(name = "description", length = 256)
    private String description;

    /**
     * The organisation can be public (visible) or private (invisible)
     */
    @Column(name = "is_public", nullable = false)
    private boolean is_public;

    /**
     * The games played by this organisation
     */
    @OneToMany(mappedBy = "organisation")
    private Set<Game> games;

    /**
     * The requests emitted to join this organisation
     */
    @OneToMany(mappedBy = "organisation")
    private List<OrgaJoinRequest> joinRequests;

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_public() {
        return is_public;
    }

    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }
}
