package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Favorite;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.FavoriteRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST}

)
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("")
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavorite(@PathVariable Long id) {
        var favorite = favoriteRepository.findById(id);
        if (favorite.isPresent())
            return new ResponseEntity<>(favorite.get(), HttpStatus.OK);
        return new ResponseEntity<>((Favorite) null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) throws BadRequestException, URISyntaxException {
        if (favorite.getId() != null)
            throw new BadRequestException("A new favorite cannot already have an ID");
        var result = favoriteRepository.save(favorite);
        return ResponseEntity
                .created(new URI("/api/favorites/" + result.getId()))
                .body(result);
    }
}
