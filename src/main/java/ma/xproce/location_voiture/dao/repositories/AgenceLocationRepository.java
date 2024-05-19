package ma.xproce.location_voiture.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.location_voiture.dao.entities.AgenceLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AgenceLocationRepository extends JpaRepository<AgenceLocation,Integer> {
 Page<AgenceLocation> findByNameContains(String keyword, Pageable pageable);
}
