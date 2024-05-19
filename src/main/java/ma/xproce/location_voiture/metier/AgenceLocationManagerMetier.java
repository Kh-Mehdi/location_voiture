package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.AgenceLocation;
import ma.xproce.location_voiture.dao.repositories.AgenceLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceLocationManagerMetier implements AgenceLocationManager {
    @Autowired
    private AgenceLocationRepository agenceLocationRepository;
    @Override
    public AgenceLocation addAgenceLocation(AgenceLocation agenceLocation) {
        return agenceLocationRepository.save(agenceLocation);
    }

    @Override
    public Page<AgenceLocation> getAllAgenceLocation(int page, int taille) {
        return agenceLocationRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Page<AgenceLocation> searchAgenceLocation(String keyword, int page, int taille) {
        return agenceLocationRepository.findByNameContains(keyword, PageRequest.of(page, taille));
    }

    @Override
    public List<AgenceLocation> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public AgenceLocation getAgenceLocationById(Integer id) {
        return agenceLocationRepository.findById(id).get();    }

    @Override
    public AgenceLocation updateAgenceLocation(AgenceLocation agenceLocation) {
        return agenceLocationRepository.save(agenceLocation);
    }

    @Override
    public boolean deleteAgenceLocation(Integer id) {
        try {
            agenceLocationRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
