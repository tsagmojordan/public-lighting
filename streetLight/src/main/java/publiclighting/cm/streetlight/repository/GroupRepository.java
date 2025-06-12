package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.streetlight.entity.StreetLightGroup;

public interface GroupRepository extends JpaRepository<StreetLightGroup, String> {
}
