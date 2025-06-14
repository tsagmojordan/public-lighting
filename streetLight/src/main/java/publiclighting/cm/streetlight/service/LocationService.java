package publiclighting.cm.streetlight.service;


import publiclighting.cm.streetlight.dto.LocationDto;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.exception.CustomException;

public interface LocationService {
    Location createLocation(LocationDto location) throws CustomException;
}
