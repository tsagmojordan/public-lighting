package publiclighting.cm.maintenance.dto;

import lombok.Builder;
import lombok.Data;

@Data@Builder
public class EquipeDto {
    private String name;
    private String communeId;
    private String communeName;
}
