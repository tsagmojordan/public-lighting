package publiclighting.cm.streetlight.service;


import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.exception.CustomException;

public interface StreetLightService {

    public StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, String streetLightGroup) throws CustomException;
}
