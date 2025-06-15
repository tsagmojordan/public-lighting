package publiclighting.cm.streetlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import publiclighting.cm.streetlight.enums.LampType;
import publiclighting.cm.streetlight.enums.State;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReduiceStreetLightResponseDto {
    private Long streetlightId;
    private State state;
    private String serialNumber;
    private PositionDto gpsPosition;
    private LampType lampType;
    private boolean isMaster;
}
