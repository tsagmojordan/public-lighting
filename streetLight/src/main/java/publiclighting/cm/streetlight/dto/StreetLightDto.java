package publiclighting.cm.streetlight.dto;

import lombok.Data;
import publiclighting.cm.streetlight.entity.GpsPosition;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.entity.StreetLightGroup;



@Data
public class StreetLightDto {
    private LocationDto location;
    private StreetLightGroup group;
    private String serialNumber;
    private GpsPosition gpsPosition;
    private boolean isMaster;


}
