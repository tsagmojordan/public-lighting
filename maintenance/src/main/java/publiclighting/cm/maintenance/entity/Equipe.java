package publiclighting.cm.maintenance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import publiclighting.cm.maintenance.entity.vo.Commune;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
    List<Maintenancier> maintenanciers;
    private String name;
    private String communeId;
    private String communeName;
}
