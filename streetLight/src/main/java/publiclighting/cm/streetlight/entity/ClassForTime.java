package publiclighting.cm.streetlight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassForTime {
    @Id @GeneratedValue(strategy = GenerationType.UUID )
    private String id;
    private int h;
    private int m;
    private int s;


    public ClassForTime(int i, int i1, int i2) {
    }
}
