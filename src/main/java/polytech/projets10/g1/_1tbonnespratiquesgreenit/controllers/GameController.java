package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Game;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.GameRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        var game = gameRepository.findById(id);
        if (game.isPresent())
            return new ResponseEntity<>(game.get(), HttpStatus.OK);
        return new ResponseEntity<>((Game) null, HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
    public ResponseEntity<Game> createGame(@RequestBody Game game) throws BadRequestException, URISyntaxException {
        if (game.getId() != null)
            throw new BadRequestException("A new game cannot already have an ID");
        var result = gameRepository.save(game);
        return ResponseEntity
                .created(new URI("/api/games/" + result.getId()))
                .body(result);
    }
}
