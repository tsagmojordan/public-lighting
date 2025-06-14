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
    private boolean isMaster=false;
    private String groupId;
    private String serialNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GpsPosition gpsPosition;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;

    @Builder(builderMethodName = "streetLightBuilder")
    public StreetLight(boolean isMaster, LightingProfile lightingProfile, String dateOf, String streetLightId, String group, String serialNumber, GpsPosition position, Lamp lamp) {
        this.streetLightId=streetLightId;
        this.isMaster=isMaster;
        this.isDeleted=isDeleted;
        this.groupId = group;
        this.gpsPosition = position;
        this.serialNumber = serialNumber;
        this.lamp = lamp;
    }


}
