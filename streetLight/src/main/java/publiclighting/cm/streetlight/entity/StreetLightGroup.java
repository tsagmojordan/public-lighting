package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StreetLightGroup extends Component{

    @Transient
    private List<Component> children;
    private boolean hasSubgroup=false;
    private boolean hasChildren=false;//if true,we will fetch them
    private String parentId = "/";//means that this group is a child group


    @Builder(builderMethodName = "groupBuilder")
    public StreetLightGroup( Date createAt,boolean isDeleted,String entityName,Date updateAt,
                            Location location,LightingProfile lightingProfile,
                            boolean hasSubgroup, boolean hasChildren) {
        super(createAt,isDeleted,entityName,updateAt,location,lightingProfile);
        this.hasSubgroup = hasSubgroup;
        this.hasChildren = hasChildren;
        this.children=new ArrayList<>();

    }


}
