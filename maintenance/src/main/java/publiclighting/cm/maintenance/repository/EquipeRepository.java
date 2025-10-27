package publiclighting.cm.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.maintenance.entity.Equipe;
import publiclighting.cm.maintenance.entity.Maintenancier;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe,String> {
    Object findByMaintenanciers(Maintenancier maintenancier);

    List<Equipe> findByCommuneId(String communeId);
}
