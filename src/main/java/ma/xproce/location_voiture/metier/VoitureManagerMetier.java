package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.Voiture;
import ma.xproce.location_voiture.dao.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoitureManagerMetier implements VoitureManager {

    @Autowired
    private VoitureRepository voitureRepository;
    @Override
    public Voiture addVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @Override
    public Page<Voiture> getAllVoiture(int page, int taille) {
        return voitureRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Page<Voiture> searchVoiture(String keyword, int page, int taille) {
        return voitureRepository.findByMarqueContains(keyword, PageRequest.of(page, taille));
    }

    @Override
    public List<Voiture> findAllVoitures() {
        return voitureRepository.findAll();
    }

    @Override
    public List<Voiture> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Voiture getVoitureById(Integer id) {
        return voitureRepository.findById(id).get();    }

    @Override
    public Voiture updateVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);

    }

    public  boolean deleteVoiture(Integer id) {
        try {
            voitureRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}



