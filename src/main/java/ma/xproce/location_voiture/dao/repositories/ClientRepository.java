package ma.xproce.location_voiture.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.location_voiture.dao.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Page<Client> findByNomContains(String keyword, Pageable pageable);
}