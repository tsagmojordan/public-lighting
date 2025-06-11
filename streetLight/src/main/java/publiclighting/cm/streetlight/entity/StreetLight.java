package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreetLight extends Component {
    @Id
    private Long streetLightId;
    @OneToOne(fetch = FetchType.LAZY)
    private Group group;
    private String serialNumber;
    private double longitude;
    private double latitude;
    private double height;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lamp lamp;

}
