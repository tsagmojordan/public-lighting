package publiclighting.cm.streetlight.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.enums.LightingProfileType;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {
    private Long id;
    private LightingProfileType lightingProfileType;
    private String zoneName;
    private Long communeId;
    private List<Component> children;

}
