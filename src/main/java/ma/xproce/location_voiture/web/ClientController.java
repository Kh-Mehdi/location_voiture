package ma.xproce.location_voiture.web;

import jakarta.validation.Valid;
import ma.xproce.location_voiture.dao.entities.Client;
import ma.xproce.location_voiture.dao.entities.ContratLocation;
import ma.xproce.location_voiture.metier.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class ClientController {

    @Autowired
    ClientManager clientManager;




    @GetMapping("/ajouterClient")
    public String ajouterClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "ajouterClient";
    }

    @PostMapping("/ajouterClient")
    public String ajouterClient(@Valid @ModelAttribute("client") Client client,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterClient";
        }
        clientManager.addClient(client);
        return "redirect:/listClient";
    }

    @GetMapping("/listClient")
    public String listClient(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "taille", defaultValue = "6") int taille,
                             @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Client> clients = clientManager.searchClients(keyword, page, taille);
        model.addAttribute("listClients", clients.getContent());
        int[] pages = new int[clients.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "clientLayout";
    }

    @GetMapping("/deleteClient")
    public String deleteClient(@RequestParam(name = "id") Integer id) {
        if (clientManager.deleteClient(id)) {
            return "redirect:/listClient";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editClient")
    public String editClient(Model model, @RequestParam(name = "id") Integer id) {
        Client client = clientManager.getClientById(id);
        if (client != null) {
            model.addAttribute("client", client);
            return "updateClient";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateClient")
    public String updateClient(@Valid @ModelAttribute Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateClient";
        }
        Client existingClient = clientManager.getClientById(client.getId());
        if (existingClient != null) {
            existingClient.setNom(client.getNom());
            existingClient.setPrenom(client.getPrenom());
            existingClient.setAdresse(client.getAdresse());
            existingClient.setEmail(client.getEmail());
            existingClient.setPassword(client.getPassword());
            // Mettez à jour d'autres champs si nécessaire
            clientManager.updateClient(existingClient);
        }
        return "redirect:/listClient";
    }

    @GetMapping("/index1")
    public String index(Model model) {
        return "index"; // Retourne la vue 'index.html'
    }
}
