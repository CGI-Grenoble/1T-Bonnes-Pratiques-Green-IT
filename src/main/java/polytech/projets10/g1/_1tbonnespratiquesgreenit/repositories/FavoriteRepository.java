package polytech.projets10.g1._1tbonnespratiquesgreenit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
