package publiclighting.cm.maintenance.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.maintenance.Exception.MaintenanceException;
import publiclighting.cm.maintenance.entity.Equipe;
import publiclighting.cm.maintenance.entity.vo.Commune;
import publiclighting.cm.maintenance.entity.vo.Maintenance;
import publiclighting.cm.maintenance.entity.vo.Panne;
import publiclighting.cm.maintenance.repository.EquipeRepository;
import publiclighting.cm.maintenance.repository.MaintenancierRepository;

import java.util.List;

@Slf4j
@Service
public class MaintenanceUseCaseImpl implements MaintenanceUseCase {
    
    private MaintenancierRepository maintenancierRepository;
    private EquipeRepository equipeRepository;
    @Override
    public Maintenance planifierMaintenance(Panne panne) throws MaintenanceException {

        //recupéré la commune concerné par la panne 
        String communeId = panne.localisation().communiId();

        //recupérer les maintenanciers de la communune
        List<Equipe>  equipe = equipeRepository.findByCommuneId(communeId);
        //programmer la date de la maintenance
        if (equipe.isEmpty()){
            throw new MaintenanceException("la communne ne dispose pas de maintenancier");
        }
        //véfifer la gravité de la panne
        switch (panne.typePanne()){
            case CAPTEUR_LUMINOSITE:
                panne.
        }

        //vérifier les emploies de temps des maintenanciers pour trouver le plus disposé à agir

        //déterminer les équipements nécessaire pour la maintenance
    
        return null;
    }

    public List<Panne> regouperPannesParZone() {
    return null;
    }
}
