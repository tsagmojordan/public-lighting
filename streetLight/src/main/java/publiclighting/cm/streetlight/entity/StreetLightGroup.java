package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StreetLightGroup extends Component{

    @Id
    private String id=UUID.randomUUID().toString();
    private List<Component> children;

    @Builder(builderMethodName = "groupBuilder")
    public StreetLightGroup(String id,String zoneName, Long municipalityId, LightingProfile lightingProfile, List<Component> children,String dataOf) {
        super(zoneName,municipalityId,lightingProfile,dataOf);
        this.lightingProfile=lightingProfile;
        this.zoneName=zoneName;
        this.municipalityId=municipalityId;
        this.id = id;
        this.children = children;
        this.entityName =dataOf;
    }

    public StreetLightGroup() {
        super();
    }


}
