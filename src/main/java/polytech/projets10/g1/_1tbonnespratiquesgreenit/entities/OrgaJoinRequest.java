package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.controllers.UserInfo;

/**
 * A request emitted by a user to join an organisation
 */
@Entity
public class OrgaJoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The organisation to be joined
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "organisation_id", referencedColumnName = "id", nullable = false)
    private Organisation organisation;

    /**
     * The user askin the request
     */
    @Column(name = "user_id", nullable = false)
    private String user_id;

    /**
     * User information
     * Useful to have the first name and the last name
     */
    @Transient
    private UserInfo userInfo;

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
