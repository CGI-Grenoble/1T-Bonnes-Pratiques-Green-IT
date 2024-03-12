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
@CrossOrigin(
        origins = "${frontend.url}",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST}

)
@RequestMapping("/api/cards")
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Card> getCard(@PathVariable Long id) {
        var card = cardRepository.findById(id);
        if (card.isPresent())
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        return new ResponseEntity<>((Card) null, HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
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
