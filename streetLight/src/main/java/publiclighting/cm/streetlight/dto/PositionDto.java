package publiclighting.cm.streetlight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto implements Serializable {
    private double longitude;
    private double latitude;
    private double hauteur;
}
