package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.AgenceLocation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


public interface AgenceLocationManager {
    public AgenceLocation addAgenceLocation(AgenceLocation agenceLocation);
    public Page<AgenceLocation> getAllAgenceLocation(int page, int taille);
    public Page<AgenceLocation> searchAgenceLocation(String keyword, int page, int taille);
    public List<AgenceLocation> getByKeyword(String keyword);
    public AgenceLocation getAgenceLocationById(Integer id);
    public AgenceLocation updateAgenceLocation(AgenceLocation agenceLocation);
    public boolean deleteAgenceLocation(Integer id);
}
