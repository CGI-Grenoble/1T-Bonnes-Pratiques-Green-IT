package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * "Sensibilisation" for "sensibilisation" cards
     * title for "expert" cards
     * title for "formation" cards
     * "Mauvaise pratique" for "mauvaise pratique" cards
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * For "bonnes pratiques" cards
     * (25, 50, 75, 100, 200)
     */
    @Column(name = "value")
    private Integer value;

    /**
     * General text
     */
    @Column(name = "description", length = 1000)
    private String description;

    /**
     * For "bonnes pratiques" cards.
     * Comma separated values among "product owner", "lead tech", "dev"
     */
    @Column(name = "actors")
    private String actors;

    /**
     * For "bonnes pratiques" cards.
     * Comma separated values among "database", "server", "computer", "devices", "internet", "engineering", "log", "planning"
     */
    @Column(name = "components")
    private String components;

    /**
     * For "bonnes pratiques" cards.
     * Comma separated values among "cpu", "ram", "disk", "network"
     */
    @Column(name = "gain_type")
    private String gain_type;

    /**
     * For "bonnes pratiques" cards.
     */
    @Column(name = "difficulty")
    private Integer difficulty;

    /**
     * Top left hand corner symbol (card type)
     */
    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "background_image", nullable = false)
    private String background_image;

    /**
     * Special title for "formation" cards
     */
    @Column(name = "subtitle")
    private String subtitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getGain_type() {
        return gain_type;
    }

    public void setGain_type(String gain_type) {
        this.gain_type = gain_type;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
