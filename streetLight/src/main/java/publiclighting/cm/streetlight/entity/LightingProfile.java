package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;
import publiclighting.cm.streetlight.enums.Ecolor;
import publiclighting.cm.streetlight.enums.LightingProfileType;

import java.io.Serializable;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightingProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Ecolor lightingColor;
    @OneToOne(cascade = CascadeType.ALL)
    private ClassForTime energySavingStartTime;
    private LightingProfileType lightingProfileType;

}
