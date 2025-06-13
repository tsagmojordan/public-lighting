package publiclighting.cm.streetlight.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComponentDto {
    private String componentId;
    private String entityName;
}
