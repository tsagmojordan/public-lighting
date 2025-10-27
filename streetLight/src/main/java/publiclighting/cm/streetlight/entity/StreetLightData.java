package publiclighting.cm.streetlight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreetLightData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String serialNumber;
    private int numberOfPresenceDetected;
    private float tension;
    private float voltage;
    private LocalDateTime dataCollectedTime;
    private boolean isLightOn;
    private float inclination;
    //add luminosity

}
