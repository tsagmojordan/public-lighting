package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.streetlight.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
