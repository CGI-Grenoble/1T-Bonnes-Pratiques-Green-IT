package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import org.springframework.web.bind.annotation.*;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Favorite;
import polytech.projets10.g1._1tbonnespratiquesgreenit.repositories.FavoriteRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("/favorites")
    public List<Favorite> getFavorites() {
        return favoriteRepository.findAll();
    }

    @PostMapping("/favorites")
    void addFavorite(@RequestBody Favorite favorite) {
        favoriteRepository.save(favorite);
    }
}
