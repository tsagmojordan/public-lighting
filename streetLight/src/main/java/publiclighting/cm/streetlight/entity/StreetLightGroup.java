package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StreetLightGroup extends Component{

    private boolean hasSubgroup=false;
    private boolean hasChildren=false;//if true,we will fetch them

    @Builder(builderMethodName = "groupBuilder")
    public StreetLightGroup(boolean hasSubgroup, String id,boolean isDeleted,Location location, LightingProfile lightingProfile,String entityName) {
        this.lightingProfile=lightingProfile;
        this.entityName =entityName;
        this.isDeleted=isDeleted;
        this.location=location;
        this.id=id;
        this.hasSubgroup=hasSubgroup;
    }

    public StreetLightGroup() {
        super();
    }



}
