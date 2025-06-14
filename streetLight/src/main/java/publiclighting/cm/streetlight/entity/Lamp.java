package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.*;
import publiclighting.cm.streetlight.enums.LampType;

import java.io.Serializable;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    @Enumerated(EnumType.STRING)
    private LampType lampType;
    private double power;//energy consumption in W
    private double lifeDuration;// for maintenance prevision
    private  double price;// for maintenance price evaluation in case where the lamp is in trouble


}
