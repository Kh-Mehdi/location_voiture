package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.AgenceLocation;
import ma.xproce.location_voiture.dao.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ClientManager {
    public Client addClient(Client client);
    public Page<Client> getAllClient(int page, int taille);
    public Page<Client> searchClient(String keyword, int page, int taille);
    public List<Client> getByKeyword(String keyword);
    public Client getClientById(Integer id);
    public Client updateClient(Client client);
    public boolean deleteClient(Integer id);

    List<Client> findAllClients();

    Page<Client> searchClients(String keyword, int page, int taille);
}
