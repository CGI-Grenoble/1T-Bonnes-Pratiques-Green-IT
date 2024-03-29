package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

/**
 * User information
 */
public class UserInfo {

    /**
     * Their id
     */
    private String id;

    /**
     * Their first name
     */
    private String firstName;

    /**
     * Their last name
     */
    private String lastName;

    public UserInfo(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
