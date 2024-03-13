package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;

import java.util.List;

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
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name= "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "varchar(1000)")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "actor")
    private String actor;

    @Column(name = "network_gain")
    private boolean network_gain;

    @Column(name = "memory_gain")
    private boolean memory_gain;

    @Column(name = "CPU_gain")
    private boolean CPU_gain;

    @Column(name = "storage_gain")
    private boolean storage_gain;

    @Column(name = "difficulty")
    private int difficulty;

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

    public boolean isNetwork_gain() {
        return network_gain;
    }

    public void setNetwork_gain(boolean network_gain) {
        this.network_gain = network_gain;
    }

    public boolean isMemory_gain() {
        return memory_gain;
    }

    public void setMemory_gain(boolean memory_gain) {
        this.memory_gain = memory_gain;
    }

    public boolean isCPU_gain() {
        return CPU_gain;
    }

    public void setCPU_gain(boolean CPU_gain) {
        this.CPU_gain = CPU_gain;
    }

    public boolean isStorage_gain() {
        return storage_gain;
    }

    public void setStorage_gain(boolean storage_gain) {
        this.storage_gain = storage_gain;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
