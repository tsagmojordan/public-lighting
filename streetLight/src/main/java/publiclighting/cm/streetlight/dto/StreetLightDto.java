package publiclighting.cm.streetlight.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import publiclighting.cm.streetlight.entity.GpsPosition;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.entity.StreetLightGroup;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreetLightDto implements Serializable {
    private String serialNumber;
    private boolean isMaster;
    private LocationDto location;
    private PositionDto gpsPosition;
    private LampDto lampDto;




}
