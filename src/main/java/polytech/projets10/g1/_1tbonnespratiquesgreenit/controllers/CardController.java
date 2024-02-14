package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Card;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.CardRepository;

import java.util.List;

@RestController
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @PostMapping("/cards")
    void addFavorite(@RequestBody Card card) {
        cardRepository.save(card);
    }
}
