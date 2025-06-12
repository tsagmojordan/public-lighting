package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LightingProfileType;

public interface LightingProfileRepository extends JpaRepository<LightingProfile,Long> {

    LightingProfile findByLightingProfileType(LightingProfileType lightingProfileType);
}
