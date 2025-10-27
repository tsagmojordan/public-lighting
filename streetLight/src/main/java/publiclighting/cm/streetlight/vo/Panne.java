package publiclighting.cm.streetlight.vo;

import jakarta.validation.constraints.NotNull;
import publiclighting.cm.streetlight.entity.GpsPosition;

import java.time.LocalDateTime;

public record Panne(
        @NotNull(message = "serial number could not be null") String numeroSerieLampadaire,
        @NotNull(message = "detection date could not be null") LocalDateTime dateDeDetection,
        @NotNull(message = "description could not be null") String description,
        @NotNull(message = "gps position could not be null") GpsPosition localisation
                    ) {
}
