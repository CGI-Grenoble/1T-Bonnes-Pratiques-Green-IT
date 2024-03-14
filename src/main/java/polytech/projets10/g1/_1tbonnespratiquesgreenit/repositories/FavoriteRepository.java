package polytech.projets10.g1._1tbonnespratiquesgreenit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.user_id = ?1")
    List<Favorite> findByUser(String userId);
}
