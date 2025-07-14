package publiclighting.cm.streetlightdatacollector.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import publiclighting.cm.streetlight.enums.State;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataSensor implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String StreetLightId;
    private boolean capteurPIR;            // Présence humaine détectée
    private double distanceUltrason;       // Distance mesurée en mètres
    private double tension;                // Tension électrique en volts
    private double luminosite;             // Luminosité ambiante en lux
    private double inclinaison;            // Inclinaison en degrés
    private boolean etatLumiere;           // Lumière allumée ou non
    private boolean microcontroleurActif;  // Système en état

}
