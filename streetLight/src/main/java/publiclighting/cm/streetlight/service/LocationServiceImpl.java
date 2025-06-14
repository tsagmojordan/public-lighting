package publiclighting.cm.streetlight.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.LocationDto;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.LocationRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {


    private final LocationRepository locationRepository;

    @Override
    public Location createLocation(LocationDto location) throws CustomException {
        Location locationEntity = locationRepository.save(Location.builder()
                .id(UUID.randomUUID().toString())
                .zoneName(location.getZoneName())
                .description(location.getDescription())
                .municipalityId(location.getMunicipalityId())
                .arrondissementId(location.getArrondissementId())
                .departmentId(location.getDepartmentId())
                .regionId(location.getRegionId())
                .build());
        log.info(Constant.LOG_DECORATION+"Location created: {}"+Constant.LOG_DECORATION, locationEntity);
        if(locationEntity.getDepartmentId() == null) {
            log.error(Constant.LOG_DECORATION+"location is null"+Constant.LOG_DECORATION);
            throw new CustomException(Constant.LOG_DECORATION+ "Location is null"+Constant.LOG_DECORATION, HttpStatus.NO_CONTENT);
        }
        return locationRepository.save(locationEntity);
    }
}
