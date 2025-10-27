package publiclighting.cm.maintenance.entity.vo;





import publiclighting.cm.maintenance.enums.TypePanne;

import java.time.LocalDateTime;

public record Panne ( String numeroSerieLampadaire,
                      LocalDateTime dateDeDetection,
                      String description,
                      Localisation localisation,
                      TypePanne typePanne
){
}
