package publiclighting.cm.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import publiclighting.cm.maintenance.entity.vo.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, String> {
}
