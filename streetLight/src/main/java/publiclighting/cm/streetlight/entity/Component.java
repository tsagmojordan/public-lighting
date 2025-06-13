package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Component extends BaseEntity {
    protected String zoneName;//neighborhood
    @OneToOne(cascade = CascadeType.ALL)
    protected Location location;
    protected Long municipalityId;
    @OneToOne(cascade = CascadeType.ALL)
    protected LightingProfile lightingProfile;

    @Builder


    public boolean componentIsDeleted(){
        return this.isDeleted;
    }

    public String getEntityName(){
        return this.entityName;
    }
    public Date getCreatedAt(){
        return this.createdAt;
    }
    public boolean getIsDeleted(){
        return this.isDeleted;
    }
    public Location getLocation(){
        return this.location;
    }
}
