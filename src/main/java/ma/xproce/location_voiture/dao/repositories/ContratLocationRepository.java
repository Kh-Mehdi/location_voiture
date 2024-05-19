package ma.xproce.location_voiture.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.xproce.location_voiture.dao.entities.ContratLocation;
import org.springframework.data.jpa.repository.Query;

public interface ContratLocationRepository extends JpaRepository<ContratLocation, Integer> {
    @Query("SELECT c FROM ContratLocation c WHERE c.voiture.marque LIKE %:keyword% OR c.client.nom LIKE %:keyword%")
    Page<ContratLocation> findByKeyword(String keyword, Pageable pageable);}
