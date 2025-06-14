package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.streetlight.entity.GpsPosition;

public interface GpsRepository extends JpaRepository<GpsPosition,String> {
}
