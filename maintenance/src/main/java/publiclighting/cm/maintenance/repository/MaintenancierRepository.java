package publiclighting.cm.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.maintenance.entity.Maintenancier;

public interface MaintenancierRepository extends JpaRepository<Maintenancier, String> {
}
