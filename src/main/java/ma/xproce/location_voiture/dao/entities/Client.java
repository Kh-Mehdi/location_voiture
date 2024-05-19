package ma.xproce.location_voiture.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name="Client")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;


    private String password ;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private Collection<ContratLocation> contratLocations;

}
