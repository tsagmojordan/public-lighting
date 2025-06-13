package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import publiclighting.cm.streetlight.entity.StreetLight;

@Repository
public interface StreetLightRepository extends JpaRepository<StreetLight, String> {
}
