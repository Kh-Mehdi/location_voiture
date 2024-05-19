package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.Voiture;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

public interface VoitureManager {
    public Voiture addVoiture(Voiture voiture);
    public Page<Voiture> getAllVoiture(int page, int taille);

    public Page<Voiture> searchVoiture(String keyword, int page, int taille) ;

    List<Voiture> findAllVoitures();

    public List<Voiture> getByKeyword(String keyword);
    public Voiture getVoitureById(Integer id);
    public Voiture updateVoiture(Voiture voiture);

    public  boolean deleteVoiture(Integer id) ;

    
}
