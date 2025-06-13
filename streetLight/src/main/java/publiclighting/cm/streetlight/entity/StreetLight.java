package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StreetLight extends Component {
    @Id
    private String streetLightId;
    private String groupId;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;

    @Builder(builderMethodName = "streetLightBuilder")
    public StreetLight(String zoneName, Long municipalityId, LightingProfile lightingProfile, String dateOf, String streetLightId, String group, String serialNumber, double longitude, double latitude, double height, Lamp lamp) {
        this.streetLightId=streetLightId;
        this.isDeleted=isDeleted;
        this.groupId = group;
        this.serialNumber = serialNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
        this.lamp = lamp;
    }


}
