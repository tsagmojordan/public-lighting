package publiclighting.cm.streetlight.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Location implements Serializable {
    @Id
    private String id;
    private String zoneName;
    private String description;
    private Long municipalityId;
    private Long arrondissementId;
    private Long departmentId;
    private Long regionId;

}
