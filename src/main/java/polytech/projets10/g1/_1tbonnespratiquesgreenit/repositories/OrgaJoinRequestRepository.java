package polytech.projets10.g1._1tbonnespratiquesgreenit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import polytech.projets10.g1._1tbonnespratiquesgreenit.entities.OrgaJoinRequest;

import java.util.List;

public interface OrgaJoinRequestRepository extends JpaRepository<OrgaJoinRequest, Long> {

    @Query("SELECT r FROM OrgaJoinRequest r WHERE r.organisation.id = ?1")
    List<OrgaJoinRequest> findByOrga(Long orgaId);

    @Query("SELECT r FROM OrgaJoinRequest r WHERE r.user_id = ?1")
    List<OrgaJoinRequest> findByUserId(String userId);
}
