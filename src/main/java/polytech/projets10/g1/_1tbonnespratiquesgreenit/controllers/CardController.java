package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Card;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.CardRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Get all cards
     * @return a list of all the cards
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    /**
     * Get one card
     * @param id the card id
     * @return one card
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Card> getCard(@PathVariable Long id) {
        var card = cardRepository.findById(id);
        if (card.isPresent())
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        return new ResponseEntity<>((Card) null, HttpStatus.NOT_FOUND);

    }

    /**
     * Add a card
     * @param card the card to be added
     * @return the card added
     * @throws BadRequestException if card already has an ID
     * @throws URISyntaxException
     */
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Card> createCard(@RequestBody Card card) throws BadRequestException, URISyntaxException {
        if (card.getId() != null)
            throw new BadRequestException("A new card cannot already have an ID");
        var result = cardRepository.save(card);
        return ResponseEntity
                .created(new URI("/api/cards/" + result.getId()))
                .body(result);
    }
}
