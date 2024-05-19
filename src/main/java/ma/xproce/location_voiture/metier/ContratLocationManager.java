package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.ContratLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContratLocationManager {
    public ContratLocation addContratLocation(ContratLocation contratLocation);

    Page<ContratLocation> searchContratLocations(String keyword, Pageable pageable);
    public ContratLocation getContratLocationById(Integer id);
    public ContratLocation updateContratLocation(ContratLocation contratLocation);
    public boolean deleteContratLocation(Integer id);


    Page<ContratLocation> findAllContratLocations(Pageable pageable);
}
