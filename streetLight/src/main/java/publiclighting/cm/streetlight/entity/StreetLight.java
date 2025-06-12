package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StreetLight extends Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streetLightId;
    private String groupId;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;

    @Builder(builderMethodName = "streetLightBuilder")
    public StreetLight(String zoneName, Long municipalityId, LightingProfile lightingProfile, String dateOf, Long streetLightId, String group, String serialNumber, double longitude, double latitude, double height, Lamp lamp) {
        super(zoneName, municipalityId, lightingProfile, dateOf);
        this.streetLightId = streetLightId;
        this.groupId = group;
        this.serialNumber = serialNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
        this.lamp = lamp;
    }


}
