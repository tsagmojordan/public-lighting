package publiclighting.cm.streetlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import publiclighting.cm.streetlight.entity.GpsPosition;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.enums.LampType;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.enums.State;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreetLightResponseDto {
    private Long streetlightId;
    private String zoneName;
    private State state;
    private LocationDto location;
    private String serialNumber;
    private PositionDto gpsPosition;
    private LampType lampType;
    private LightingProfileType lightingProfile;
    private boolean isMaster;

}
