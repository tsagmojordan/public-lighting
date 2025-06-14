package publiclighting.cm.streetlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements Serializable {
    private String zoneName;
    private String description;
    private Long municipalityId;
    private Long arrondissementId;
    private Long departmentId;
    private Long regionId;
}
