package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.ContratLocation;
import ma.xproce.location_voiture.dao.repositories.ContratLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContratLocationManagerMetier implements ContratLocationManager {

    @Autowired
    private ContratLocationRepository contratLocationRepository;

    @Override
    public ContratLocation addContratLocation(ContratLocation contratLocation) {
        return contratLocationRepository.save(contratLocation);
    }

    @Override
    public Page<ContratLocation> searchContratLocations(String keyword, Pageable pageable) {
        return contratLocationRepository.findByKeyword(keyword, pageable);
    }

    @Override
    public ContratLocation getContratLocationById(Integer id) {
        return contratLocationRepository.findById(id).orElse(null);
    }

    @Override
    public ContratLocation updateContratLocation(ContratLocation contratLocation) {
        return contratLocationRepository.save(contratLocation);
    }

    @Override
    public boolean deleteContratLocation(Integer id) {
        try {
            contratLocationRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Page<ContratLocation> findAllContratLocations(Pageable pageable) {
        return contratLocationRepository.findAll(pageable);
    }


}
