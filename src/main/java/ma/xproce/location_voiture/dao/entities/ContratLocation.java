package ma.xproce.location_voiture.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ContratLocation")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContratLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int nb_j;

    private Double montantTotal;

    @ManyToOne
    private Voiture voiture;

    @ManyToOne
    private Client client;

    // Méthode pour calculer le montant total
    public Double calculateMontantTotal() {
        if (nb_j != 0 && voiture.getPrixJour() != 0) {
            montantTotal = voiture.getPrixJour() * nb_j;

        } else {
            montantTotal = 0.0; // Valeur par défaut si les dates ou le prix sont nuls
        }
        return montantTotal;
    }
    void setDisponible(Boolean disponible) {
        voiture.setDispo(false);
    }



}
