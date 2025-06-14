package publiclighting.cm.streetlight.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    //add unique condition here
    private String zoneName;
    private String description;
    private Long municipalityId;
    private Long arrondissementId;
    private Long departmentId;
    private Long regionId;

}
