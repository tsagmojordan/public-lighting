package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StreetLight extends Component {

    private boolean isMaster=false;
    private String groupId="";//parent id
    private String serialNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GpsPosition gpsPosition;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;

    @Builder(builderMethodName = "streetLightBuilder")

    public StreetLight(String id, Date createAt, boolean isDeleted, String entityName, Date updateAt,
                       Location location, LightingProfile lightingProfile, String streetLightId, boolean isMaster, String groupId, String serialNumber, GpsPosition gpsPosition, Lamp lamp) {
        super(id, createAt, isDeleted, entityName, updateAt, location, lightingProfile);
        this.isMaster = isMaster;
        this.groupId = groupId;
        this.serialNumber = serialNumber;
        this.gpsPosition = gpsPosition;
        this.lamp = lamp;
    }


}
