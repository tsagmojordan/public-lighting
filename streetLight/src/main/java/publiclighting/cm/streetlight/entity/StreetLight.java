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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streetLightId;
    @OneToOne(fetch = FetchType.LAZY)
    private StreetLightGroup group;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;
//    @Builder(builderMethodName = "groupBuilder")
//    public Streetlight(String zoneName, Long municipalityId, LightingProfile lightingProfile, StreetLightGroup group,String serialNumber,do) {
//        super(zoneName,municipalityId,lightingProfile);
//        this.children = children;
//    }
}
