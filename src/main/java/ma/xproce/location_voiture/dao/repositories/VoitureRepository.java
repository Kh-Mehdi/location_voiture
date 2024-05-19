package ma.xproce.location_voiture.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.location_voiture.dao.entities.Voiture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface VoitureRepository extends JpaRepository<Voiture,Integer> {
    Page<Voiture> findByMarqueContains(String keyword, Pageable pageable);

}
