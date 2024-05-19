package ma.xproce.location_voiture.metier;

import ma.xproce.location_voiture.dao.entities.Client;
import ma.xproce.location_voiture.dao.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientManagerMetier implements ClientManager{

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);

    }

    @Override
    public Page<Client> getAllClient(int page, int taille) {
        return clientRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Page<Client> searchClient(String keyword, int page, int taille) {
        return clientRepository.findByNomContains(keyword, PageRequest.of(page, taille));

    }

    @Override
    public List<Client> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.findById(id).get();    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean deleteClient(Integer id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> searchClients(String keyword, int page, int taille) {
        return clientRepository.findByNomContains(keyword, PageRequest.of(page, taille));
    }

}



