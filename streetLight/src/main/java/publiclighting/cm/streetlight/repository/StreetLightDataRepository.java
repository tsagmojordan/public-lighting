package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.streetlight.entity.StreetLightData;

public interface StreetLightDataRepository extends JpaRepository<StreetLightData,String> {
}
