package ma.xproce.location_voiture;

import ma.xproce.location_voiture.dao.entities.Voiture;
import ma.xproce.location_voiture.metier.VoitureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootApplication
public class LocationVoitureApplication implements CommandLineRunner {

    @Autowired
    VoitureManager voitureManager;

    public static void main(String[] args) {
        SpringApplication.run(LocationVoitureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Get the first page with a single element to check if the database is empty
        Page<Voiture> firstPage = voitureManager.getAllVoiture(0, 1);

        // Check if the returned page is empty
        if (firstPage.isEmpty()) {
            // Add voiture1 only if the database is empty
            Voiture voiture1 = new Voiture(null, "Mercedes", "classe e", 2016, 450, true, null, null);
            voitureManager.addVoiture(voiture1);
        }
    }


}
