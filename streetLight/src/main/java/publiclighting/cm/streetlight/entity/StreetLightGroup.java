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
    @Column(length = 100000)
    private List<Component> children;

    @Builder(builderMethodName = "groupBuilder")
    public StreetLightGroup(boolean isDeleted,Location location,String zoneName, Long municipalityId, LightingProfile lightingProfile, List<Component> children,String dataOf) {
        this.lightingProfile=lightingProfile;
        this.zoneName=zoneName;
        this.municipalityId=municipalityId;
        this.children = children;
        this.entityName =dataOf;
        this.isDeleted=isDeleted;
        this.location=location;
    }

    public StreetLightGroup() {
        super();
    }



}
