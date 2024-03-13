package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrgaJoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "organisation_id", referencedColumnName = "id", nullable = false)
    private Organisation organisation;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
