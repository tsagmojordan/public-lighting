package publiclighting.cm.streetlight.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GpsPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double longitude;
    private double latitude;
    private double hauteur;


}
