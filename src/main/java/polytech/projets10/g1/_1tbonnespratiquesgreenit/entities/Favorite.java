package polytech.projets10.g1._1tbonnespratiquesgreenit.entities;

import jakarta.persistence.*;

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Enumerated(EnumType.STRING)
    @Column(name= "category", nullable = false, columnDefinition = "varchar(255) default 'UNSEEN'")
    private FavoriteType category;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "card_id", referencedColumnName = "id", nullable = false)
    private Card card;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public FavoriteType getCategory() {
        return category;
    }

    public void setCategory(FavoriteType category) {
        this.category = category;
    }
}
