package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Component extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    protected Location location;
    @OneToOne(cascade = CascadeType.ALL)
    protected LightingProfile lightingProfile;


    protected Component(String id, Date createAt, boolean isDeleted, String entityName, Date updateAt, Location location, LightingProfile lightingProfile) {
        super(id, createAt, isDeleted, entityName, updateAt);
        this.location = location;
        this.lightingProfile=lightingProfile;

    }

    protected Component() {

    }

    public boolean componentIsDeleted(){
        return this.isDeleted;
    }

}
