package ma.xproce.location_voiture.web;

import jakarta.validation.Valid;
import ma.xproce.location_voiture.dao.entities.AgenceLocation;
import ma.xproce.location_voiture.dao.entities.ContratLocation;
import ma.xproce.location_voiture.dao.entities.Voiture;
import ma.xproce.location_voiture.metier.VoitureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class VoitureController {



    @Autowired
    VoitureManager voitureManager;



    @GetMapping("/ajouter")
    public String ajouterVoitureAction(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "marque") String marque,
            @RequestParam(name = "modele") String modele,
            @RequestParam(name = "annee") Integer annee,
            @RequestParam(name = "prixJour") double prixJour,
            @RequestParam(name = "agenceLocation") AgenceLocation agenceLocation,
            @RequestParam(name = "contratLocations") Collection<ContratLocation> contratLocations
    ) {
        Voiture voiture = new Voiture(id, marque, modele, annee, prixJour, true, null, null);
        voitureManager.addVoiture(voiture);
        return "redirect:/listVoiture";
    }

    @PostMapping("/ajouterOnce")
    public String ajouterVoiture(Model model,
                                 @Valid Voiture voiture,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterVoiture";
        }
        voitureManager.addVoiture(voiture);
        return "redirect:/listVoiture";
    }

    @GetMapping("/ajouterVoiture")
    public String ajouterVoitureForm(Model model) {
        model.addAttribute("voiture", new Voiture());
        return "ajouterVoiture";
    }

    @GetMapping("/listVoiture")
    public String listVoiture(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "taille", defaultValue = "6") int taille,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Voiture> voitures = voitureManager.searchVoiture(keyword, page, taille);
        model.addAttribute("listVoitures", voitures.getContent());
        int[] pages = new int[voitures.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "voiturelayout";
    }

    @GetMapping("/deleteVoiture")
    public String deleteVoiture(@RequestParam(name = "id") Integer id) {
        if (voitureManager.deleteVoiture(id)) {
            return "redirect:/listVoiture";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editVoiture")
    public String editVoiture(Model model, @RequestParam(name = "id") Integer id) {
        Voiture voiture = voitureManager.getVoitureById(id);
        if (voiture != null) {
            model.addAttribute("voiture", voiture);
            return "updateVoiture";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateVoiture")
    public String updateVoiture(@Valid @ModelAttribute Voiture voiture, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateVoiture";
        }
        Voiture existingVoiture = voitureManager.getVoitureById(voiture.getId());
        if (existingVoiture != null) {
            existingVoiture.setMarque(voiture.getMarque());
            existingVoiture.setModele(voiture.getModele());
            existingVoiture.setAnnee(voiture.getAnnee());
            existingVoiture.setPrixJour(voiture.getPrixJour());
            // Mettez à jour d'autres champs si nécessaire
            voitureManager.updateVoiture(existingVoiture);
        }
        return "redirect:/listVoiture";
    }

}
