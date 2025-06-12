package publiclighting.cm.streetlight.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.LightingProfile;

import java.util.List;

@Data
public class GroupDto {
    @NotNull(message = "a group should have a neighborhood name")
    private String zoneName;//neighborhood
    @NotNull(message = "please mention municipality where it's located")
    private Long municipalityId;

}
