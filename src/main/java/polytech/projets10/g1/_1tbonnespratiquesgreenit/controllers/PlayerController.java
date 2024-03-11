package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Player;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.PlayerRepository;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET }
)
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable String id) {
        var player = playerRepository.findById(id);
        if (player.isPresent())
            return new ResponseEntity<>(player.get(), HttpStatus.OK);
        return new ResponseEntity<>((Player) null, HttpStatus.NOT_FOUND);

    }
}
