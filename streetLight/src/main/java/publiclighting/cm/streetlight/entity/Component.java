package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class Component extends BaseEntity {
    protected String zoneName;//neighborhood
    protected Long municipalityId;
    @OneToOne(cascade = CascadeType.ALL)
    protected LightingProfile lightingProfile;



    public Component() {

    }

    public Component(String zoneName, Long municipalityId, LightingProfile lightingProfile, String dateOf) {
        super();
    }
}
