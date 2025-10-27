package publiclighting.cm.maintenance.entity.vo;

public record Localisation(
         String id,
         double longitude,
         double latitude,
         double hauteur,
         String communiId
) {
}
