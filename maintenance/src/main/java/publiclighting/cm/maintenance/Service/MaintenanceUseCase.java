package publiclighting.cm.maintenance.Service;

import publiclighting.cm.maintenance.Exception.MaintenanceException;
import publiclighting.cm.maintenance.entity.vo.Maintenance;
import publiclighting.cm.maintenance.entity.vo.Panne;

public interface MaintenanceUseCase {
    Maintenance planifierMaintenance(Panne panne) throws MaintenanceException;
}
