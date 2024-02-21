package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Card;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.CardRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
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
