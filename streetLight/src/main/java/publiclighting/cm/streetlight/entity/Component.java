package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Component extends BaseEntity {
    @Id
    protected String id;
    @OneToOne(cascade = CascadeType.ALL)
    protected Location location;
    @OneToOne(cascade = CascadeType.ALL)
    protected LightingProfile lightingProfile;

    public boolean componentIsDeleted(){
        return this.isDeleted;
    }

}
