package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Game;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.GameRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @PostMapping("/games")
    void addGame(@RequestBody Game game) {
        gameRepository.save(game);
    }
}
