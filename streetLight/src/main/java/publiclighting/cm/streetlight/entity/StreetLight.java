package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;
import publiclighting.cm.streetlight.enums.State;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StreetLight extends Component {

    private boolean isMaster=false;
    private String parentId ="";//parent id
    private String serialNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GpsPosition gpsPosition;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;
    @Enumerated(EnumType.STRING)
    private State state=State.OFF;

    private boolean isIntegrate=false; //le lampadaire possède un module smart de smep?

    @Builder(builderMethodName = "streetLightBuilder")
    public StreetLight( Date createAt, boolean isDeleted, String entityName, Date updateAt,
                       Location location, LightingProfile lightingProfile,
                       boolean isMaster, String groupId, String serialNumber,
                       GpsPosition gpsPosition, Lamp lamp,State state) {
        super( createAt, isDeleted, entityName, updateAt, location, lightingProfile);
        this.isMaster = isMaster;
        this.parentId = groupId;
        this.serialNumber = serialNumber;
        this.gpsPosition = gpsPosition;
        this.lamp = lamp;
        this.state = state;
    }


}
