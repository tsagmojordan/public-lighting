package publiclighting.cm.streetlight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group extends Component{

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String groupId;
    private List<Component> children;

}
