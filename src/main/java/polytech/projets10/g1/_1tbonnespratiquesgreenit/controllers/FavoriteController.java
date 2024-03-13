package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Favorite;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.FavoriteRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * Get all favorites
     * @return all the favorites
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    /**
     * Get one favorite
     * @param id the favorite id
     * @return the favorite
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Favorite> getFavorite(@PathVariable Long id) {
        var favorite = favoriteRepository.findById(id);
        if (favorite.isPresent())
            return new ResponseEntity<>(favorite.get(), HttpStatus.OK);
        return new ResponseEntity<>((Favorite) null, HttpStatus.NOT_FOUND);
    }

    /**
     * Modify a favorite
     * @param id the favorite id
     * @param favorite the favorite modified
     * @return the favorite modified
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable Long id, @RequestBody @Valid Favorite favorite) {
        if (favorite.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: null");
        if (!Objects.equals(id, favorite.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id: invalid");
        if (!favoriteRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity not found");

        Favorite res = favoriteRepository.save(favorite);
        return ResponseEntity.ok().body(res);
    }

    /**
     * Get all favorites for a user
     * @param userId the user ID
     * @return the user favorites
     */
    @GetMapping("/forUser/{userId}")
    public List<Favorite> getFavoritesForUser(@PathVariable Long userId) {
        return favoriteRepository.findByUser(userId);
    }

    /**
     * Add a favorite
     * @param favorite the favorite to be added
     * @return the favorite added
     * @throws BadRequestException if favorite already has an ID
     * @throws URISyntaxException
     */
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) throws BadRequestException, URISyntaxException {
        if (favorite.getId() != null)
            throw new BadRequestException("A new favorite cannot already have an ID");
        var result = favoriteRepository.save(favorite);
        return ResponseEntity
                .created(new URI("/api/favorites/" + result.getId()))
                .body(result);
    }

    /**
     * Delete a favorite
     * @param id the favorite to delete
     * @return a success response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
