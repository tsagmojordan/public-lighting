package publiclighting.cm.streetlight.service;

import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.entity.Lamp;

public interface LampService {

    Lamp createLamp(LampDto lampDto);
}
