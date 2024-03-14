package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Game;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Organisation;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.enums.GameStatus;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.GameRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;

    @Autowired
    Keycloak keycloak;

    @Value("${keycloak.config.realm}")
    private String realm;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Get all games
     * @return all games
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Get a game
     * @param id the game id
     * @return the game
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        var game = gameRepository.findById(id);
        if (game.isPresent()) return ResponseEntity.ok(game.get());
        return ResponseEntity.ok(null);

    }

    /**
     * Modify a game
     * @param id the game id
     * @param game the game modified
     * @return the game modified
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody @Valid Game game) {
        if (game.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: null");
        if (!Objects.equals(id, game.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: invalid");
        if (!gameRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity not found");

        Game res = gameRepository.save(game);
        return ResponseEntity.ok().body(res);
    }

    /**
     * Create a game
     * @param organisation the organisation hosting the game
     * @return the game created
     * @throws URISyntaxException
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Game> createGame(@RequestBody Organisation organisation) throws URISyntaxException {
        Game game = new Game();
        game.setDate(new Date());
        game.setStatus(GameStatus.WAITING_TO_START);
        game.setOrganisation(organisation);
        var result = gameRepository.save(game);
        return ResponseEntity.created(new URI("/api/games/" + result.getId())).body(result);
    }

    /**
     * A user joins a game
     * @param gameId the game id
     * @param userId the user id
     * @return the game
     */
    @PostMapping("/{gameId}/join")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Game> userJoinsGame(@PathVariable Long gameId, @RequestBody String userId) {
        var game = gameRepository.findById(gameId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette partie n'existe pas: " + gameId);

        Game gameObj = game.get();
        if (gameObj.getStatus() != GameStatus.WAITING_TO_START)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette partie est en cours: " + gameId);

        if (this.getPlayersCountInGame(gameId) >= 4)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette partie est pleine: " + gameId);

        UserResource user = keycloak.realm(this.realm).users().get(userId);
        try {
            UserRepresentation userRepresentation = user.toRepresentation();
            Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();


            if(existingAttributes.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no attributes");

            List<String> userGames = existingAttributes.get("game");

            if (userGames == null) userGames = new ArrayList<>();
            if (userGames.contains(String.valueOf(gameId)))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur est déjà dans cette partie: " + gameId);

            userGames.add(String.valueOf(gameId));

            existingAttributes.put("game", userGames);
            user.update(userRepresentation);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + userId, e);
        }
    }

    /**
     * A user leaves a game
     * @param gameId the game id
     * @param userId the user id
     * @return the game
     */
    @PostMapping("/{gameId}/leave")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Game> userLeavesGame(@PathVariable Long gameId, @RequestBody String userId) {
        var game = gameRepository.findById(gameId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette partie n'existe pas: " + gameId);

        UserResource user = keycloak.realm(this.realm).users().get(userId);
        try {
            UserRepresentation userRepresentation = user.toRepresentation();
            Map<String, List<String>> existingAttributes = userRepresentation.getAttributes();

            if(existingAttributes.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no attributes");

            List<String> userGames = existingAttributes.get("game");

            if (userGames == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur (" + userId + ") ne fait partie d'aucune partie");
            if (!userGames.contains(String.valueOf(gameId)))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet utilisateur n'appartient pas à cette partie: " + gameId);

            userGames.remove(String.valueOf(gameId));

            existingAttributes.put("game", userGames);

            user.update(userRepresentation);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas: " + userId, e);
        }
    }

    /**
     * Delete a game
     * @param id the game id
     * @return a success response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    private int getPlayersCountInGame(Long gameId) {
        List<UserRepresentation> users = keycloak.realm(realm).users().searchByAttributes("game:" + gameId);
        return users.size();
    }
}
