package publiclighting.cm.maintenance.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaintenancierDto {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private boolean isHeadOfTeam;
}
