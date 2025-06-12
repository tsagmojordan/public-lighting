package publiclighting.cm.streetlight.dto;


import lombok.Builder;
import lombok.Data;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LightingProfileType;

import java.util.List;

@Builder
@Data
public class GroupResponseDto {
    private String id;
    private String zoneName;//neighborhood
    private Long municipalityId;
    private LightingProfileType lightingProfileType;
    private List<Component> children;
}
