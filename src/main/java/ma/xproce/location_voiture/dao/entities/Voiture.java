package ma.xproce.location_voiture.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.xproce.location_voiture.dao.entities.AgenceLocation;
import java.util.Collection;

@Entity
@Table(name = "Voiture")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marque;
    private String modele;
    private Integer annee;
    private double prixJour;
    private boolean dispo=true;


    @ManyToOne
    private AgenceLocation agenceLocation;

    @OneToMany(mappedBy = "voiture", fetch = FetchType.LAZY)
    private Collection<ContratLocation> contratLocations;



}
