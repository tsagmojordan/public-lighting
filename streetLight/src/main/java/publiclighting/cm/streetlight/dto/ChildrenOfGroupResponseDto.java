package publiclighting.cm.streetlight.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ChildrenOfGroupResponseDto {
    private String componentId;
    private String entityName;
    private Date createdAt;
}
