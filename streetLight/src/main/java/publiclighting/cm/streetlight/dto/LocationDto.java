package publiclighting.cm.streetlight.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {
    private String zoneName;
    private String description;
    private Long municipalityId;
    private Long arrondissementId;
    private Long departmentId;
    private Long regionId;
}
