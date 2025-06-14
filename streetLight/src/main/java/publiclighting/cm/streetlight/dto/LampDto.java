package publiclighting.cm.streetlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import publiclighting.cm.streetlight.enums.LampType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LampDto {
    private LampType lampType;
    private double power;//energy consumption in W
    private double lifeDuration;// for maintenance prevision
    private  double price;// for maintenance price evaluation in case where the lamp is in trouble


}
