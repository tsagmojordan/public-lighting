package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.repository.LocationRepository;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {


    private final LocationRepository locationRepository;
    @Override @Transactional
    public void createLocation(Location location) {
        locationRepository.save(location);
    }
}
