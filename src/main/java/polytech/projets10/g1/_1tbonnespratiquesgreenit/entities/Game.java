package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.enums.GameStatus;

import java.util.Date;

/**
 * A game of "1t de bonnes pratiques"
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Creation date
     */
    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date date;

    /**
     * "WAITING_TO_START", "PLAYING", "OVER"
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "varchar(255) default 'WAITING_TO_START'")
    private GameStatus status;

    /**
     * The organisation hosting the game
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "organisation_id", referencedColumnName = "id", nullable = false)
    private Organisation organisation;

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
