package publiclighting.cm.streetlight.dto;

import lombok.Builder;
import lombok.Data;

import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.enums.LampType;

@Data
@Builder
public class StreetLightResponseDto {
    private String streetlightId;
    private String groupId;
    private Location location;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;
    private LampType lampType;
    private LightingProfile lightingProfile;
    private boolean isMaster;

}
