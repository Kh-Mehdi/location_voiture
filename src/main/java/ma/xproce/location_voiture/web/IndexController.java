package ma.xproce.location_voiture.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        return "index"; // Retourne la vue 'index.html'
    }

    @GetMapping("/clients")
    public String redirectToClientList() {
        return "clientLayout";
    }

    @GetMapping("/contrats")
    public String redirectToContratList() {
        return "contratLayout";
    }

    @GetMapping("/voitures")
    public String redirectToVoitureList() {
        return "voitureLayout";
    }
}
