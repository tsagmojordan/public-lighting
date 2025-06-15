package publiclighting.cm.streetlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenResponseDto {
    private String parentId;
    private String parentZoneName;
    private String parentCommuneId;
    private ComponentDto children;
}
