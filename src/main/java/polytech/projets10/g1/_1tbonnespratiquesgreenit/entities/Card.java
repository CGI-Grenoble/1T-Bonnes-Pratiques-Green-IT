package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;

/**
 * A playing card
 */
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * "Sensibilisation" / "Formation" / "Expert" / "Mauvaise pratique" / "XXKg"
     */
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * Title of the card
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Card text
     */
    @Column(name = "description", nullable = false, columnDefinition = "varchar(1000)")
    private String description;

    /**
     * Link for formation cards
     */
    @Column(name = "link")
    private String link;

    /**
     * Actor of the practice
     */
    @Column(name = "actor")
    private String actor;

    /**
     * True if practice allows network gain
     */
    @Column(name = "network_gain")
    private Boolean network_gain;

    /**
     * True if practice allows memory gain
     */
    @Column(name = "memory_gain")
    private Boolean memory_gain;

    /**
     * True if practice allows CPU gain
     */
    @Column(name = "CPU_gain")
    private Boolean CPU_gain;

    /**
     * True if practice allows storage gain
     */
    @Column(name = "storage_gain")
    private Boolean storage_gain;

    /**
     * Difficulty of the practice
     */
    @Column(name = "difficulty")
    private Integer difficulty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Boolean isNetwork_gain() {
        return network_gain;
    }

    public void setNetwork_gain(Boolean network_gain) {
        this.network_gain = network_gain;
    }

    public Boolean isMemory_gain() {
        return memory_gain;
    }

    public void setMemory_gain(Boolean memory_gain) {
        this.memory_gain = memory_gain;
    }

    public Boolean isCPU_gain() {
        return CPU_gain;
    }

    public void setCPU_gain(Boolean CPU_gain) {
        this.CPU_gain = CPU_gain;
    }

    public Boolean isStorage_gain() {
        return storage_gain;
    }

    public void setStorage_gain(Boolean storage_gain) {
        this.storage_gain = storage_gain;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}
