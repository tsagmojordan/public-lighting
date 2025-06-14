package publiclighting.cm.streetlight.dto;


import lombok.Builder;
import lombok.Data;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.enums.LightingProfileType;


@Builder
@Data
public class GroupResponseDto {
    private String id;
    private LightingProfileType lightingProfileType;
    private Location location;
    private boolean hasSubgroup;
}
