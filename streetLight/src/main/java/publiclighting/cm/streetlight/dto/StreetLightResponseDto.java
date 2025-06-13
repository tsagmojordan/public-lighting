package publiclighting.cm.streetlight.dto;

import lombok.Builder;
import lombok.Data;

import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LampType;

@Data
@Builder
public class StreetLightResponseDto {
    private String streetlightId;
    private String zoneName;
    private Long municipalityId;
    private String groupId;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;
    private LampType lampType;
    private LightingProfile lightingProfile;

}
