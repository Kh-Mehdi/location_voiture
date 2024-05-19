package ma.xproce.location_voiture.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name="AgenceLocation")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgenceLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String adresse;
    private String telephone;
    @OneToMany(mappedBy ="agenceLocation", fetch = FetchType.LAZY)
    private Collection<Voiture> voiture;
}
