package publiclighting.cm.streetlight.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @OneToOne(cascade = CascadeType.ALL) @JsonSerialize
    private ClassForTime energySavingStartTime;
    private LightingProfileType lightingProfileType;

}
