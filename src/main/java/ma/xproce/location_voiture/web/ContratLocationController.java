package ma.xproce.location_voiture.web;

import jakarta.validation.Valid;
import ma.xproce.location_voiture.dao.entities.Client;
import ma.xproce.location_voiture.dao.entities.ContratLocation;
import ma.xproce.location_voiture.dao.entities.Voiture;
import ma.xproce.location_voiture.metier.ClientManager;
import ma.xproce.location_voiture.metier.ContratLocationManager;
import ma.xproce.location_voiture.metier.VoitureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContratLocationController {

    @Autowired
    private ContratLocationManager contratLocationManager;

    @Autowired
    private VoitureManager voitureManager;

    @Autowired
    private ClientManager clientManager;

    @GetMapping("/ajouterContrat")
    public String ajouterContratForm(Model model) {
        model.addAttribute("contrat", new ContratLocation());
        model.addAttribute("voitures", voitureManager.findAllVoitures());
        model.addAttribute("clients", clientManager.findAllClients());
        return "ajouterContrat";
    }

    @PostMapping("/ajouterContrat")
    public String ajouterContrat(@Valid @ModelAttribute("contrat") ContratLocation contrat,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("voitures", voitureManager.findAllVoitures());
            model.addAttribute("clients", clientManager.findAllClients());
            return "ajouterContrat";
        }
        contrat.calculateMontantTotal();
        contrat.getVoiture().setDispo(false);
        contratLocationManager.addContratLocation(contrat);
        return "redirect:/listContrat";
    }

    @GetMapping("/listContrat")
    public String listContrat(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "taille", defaultValue = "6") int taille,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        if (taille < 1) {
            taille = 6;
        }

        Pageable pageable = PageRequest.of(page, taille);
        Page<ContratLocation> contrats;

        if (keyword != null && !keyword.isEmpty()) {
            contrats = contratLocationManager.searchContratLocations(keyword, pageable);
        } else {
            contrats = contratLocationManager.findAllContratLocations(pageable);
        }

        model.addAttribute("listContrats", contrats.getContent());
        model.addAttribute("pages", new int[contrats.getTotalPages()]);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);

        return "contratLayout";
    }

    @GetMapping("/deleteContrat")
    public String deleteContrat(@RequestParam(name = "id") Integer id) {
        ContratLocation contrat = contratLocationManager.getContratLocationById(id);
        if (contrat != null) {
            Voiture voiture = contrat.getVoiture();
            voiture.setDispo(true);
            contratLocationManager.deleteContratLocation(id);
        }
        return "redirect:/listContrat";
    }

    @GetMapping("/editContrat")
    public String editContrat(Model model, @RequestParam(name = "id") Integer id) {
        ContratLocation contrat = contratLocationManager.getContratLocationById(id);
        if (contrat != null) {
            model.addAttribute("contrat", contrat);
            model.addAttribute("voitures", voitureManager.findAllVoitures());
            model.addAttribute("clients", clientManager.findAllClients());
            return "updateContrat";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateContrat")
    public String updateContrat(@Valid @ModelAttribute("contrat") ContratLocation contrat,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("voitures", voitureManager.findAllVoitures());
            model.addAttribute("clients", clientManager.findAllClients());
            return "updateContrat";
        }
        ContratLocation existingContrat = contratLocationManager.getContratLocationById(contrat.getId());
        if (existingContrat != null) {
            existingContrat.setNb_j(contrat.getNb_j());
            existingContrat.calculateMontantTotal();
            contratLocationManager.updateContratLocation(existingContrat);
        }
        return "redirect:/listContrat";
    }
}
