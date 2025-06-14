package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;

@Service
@AllArgsConstructor

public class LightingProfileImpl implements LightingProfileService {

    private final LightingProfileRepository lightingProfileRepository;
    @Override
    public LightingProfile getDefaultLightingProfile() {
        return lightingProfileRepository.findByLightingProfileType(LightingProfileType.DEFAULT);
    }
}
