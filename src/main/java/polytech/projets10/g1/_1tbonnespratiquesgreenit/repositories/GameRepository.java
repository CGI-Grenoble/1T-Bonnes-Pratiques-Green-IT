package polytech.projets10.g1._1tbonnespratiquesgreenit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
