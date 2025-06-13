package publiclighting.cm.streetlight.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import publiclighting.cm.streetlight.entity.Location;

@Data
public class GroupDto {

    private Location location;
}
