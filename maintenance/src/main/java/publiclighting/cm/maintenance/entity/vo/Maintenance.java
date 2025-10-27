package publiclighting.cm.maintenance.entity.vo;

import jakarta.persistence.*;
import publiclighting.cm.maintenance.entity.Equipe;
import publiclighting.cm.maintenance.enums.TypeDeMaintenance;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public record Maintenance(
        @Id String maintenanceId,
        LocalDateTime dateCreation,
        Date dateMaintenance,
        int heureDebut,
        int heureFin,
        @OneToMany(cascade = CascadeType.ALL)
        List<Equipe> equipes,
        TypeDeMaintenance typeDeMaintenance) {
}
