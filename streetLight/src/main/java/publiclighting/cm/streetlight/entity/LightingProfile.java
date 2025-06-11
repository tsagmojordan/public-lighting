package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;
import publiclighting.cm.streetlight.enums.Ecolor;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightingProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Ecolor lightingColor;
    private

}
