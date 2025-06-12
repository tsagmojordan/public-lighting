package publiclighting.cm.streetlight.dto;

import lombok.Data;
import publiclighting.cm.streetlight.entity.StreetLightGroup;



@Data
public class StreetLightDto {
    private String zoneName;
    private StreetLightGroup group;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;


}
