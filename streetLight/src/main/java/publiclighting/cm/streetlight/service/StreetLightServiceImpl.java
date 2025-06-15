package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.entity.*;
import publiclighting.cm.streetlight.enums.State;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.*;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StreetLightServiceImpl implements StreetLightService {


    private final StreetLightRepository streetLightRepository;
    private final LampService lampService;
    private final GroupRepository groupRepository;
    private final GpsRepository gpsRepository;
    private final LightingProfileService lightingProfileService;
    private final LightingProfileRepository lightingProfileRepository;

    @Override
    public StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, String streetLightGroup) throws CustomException {
        Lamp lamp = lampService.createLamp(lampDto);
        double serialNumberGen=Math.round(Math.random()*1000000000000000000L) + (Math.random()*1250000000000000L);
        GpsPosition gpsPosition = GpsPosition.builder()
                .hauteur(streetLightDto.getGpsPosition().getHauteur())
                .longitude(streetLightDto.getGpsPosition().getLongitude())
                .latitude(streetLightDto.getGpsPosition().getLatitude())
                .build();
        gpsRepository.save(gpsPosition);
        StreetLightGroup group = groupRepository.findById(streetLightGroup).orElseThrow(() -> new CustomException(Constant.LOG_DECORATION + "group doesn't exist" + Constant.LOG_DECORATION, null));
        Location location = group.getLocation();
        LightingProfile lightingProfile = lightingProfileRepository.findById(group.getLightingProfile().getId()).orElseThrow();
        StreetLight streetLight = StreetLight.streetLightBuilder()
                .groupId(String.valueOf(group.getId()))
                .createAt(new Date())
                .isMaster(streetLightDto.isMaster())
                .updateAt(new Date())
                .isDeleted(false)
                .build();
        streetLight.setEntityName("StreetLight");
        streetLight.setGpsPosition(gpsPosition);
        streetLight.setLocation(location);
        streetLight.setLamp(lamp);
        streetLight.setLightingProfile(lightingProfile);
        streetLight.setSerialNumber("STREETLIGHT-" +serialNumberGen);
        streetLight.setState(State.OFF);
        group.setHasChildren(true);
        groupRepository.save(group);
        streetLightRepository.save(streetLight);


        return StreetLightResponseDto.builder()
                .gpsPosition(streetLightDto.getGpsPosition())
                .serialNumber(streetLight.getSerialNumber())
                .lampType(lamp.getLampType())
                .zoneName(streetLight.getLocation().getZoneName())
                .isMaster(streetLightDto.isMaster())
                .state(State.OFF)
                .lampType(lamp.getLampType())
                .lightingProfile(streetLight.getLightingProfile().getLightingProfileType())
                .build();
    }

    @Override
    public Component findById(String componentId) {
        return null;
    }

    @Override
    public List<Component> findAllByGroup(String id) {
        return streetLightRepository.findAllByParentId(id).stream()
                .filter(c -> !c.isDeleted())
                .toList();
    }

    @Override
    public StreetLight findById(Long id) throws CustomException {
        StreetLight streetLight= streetLightRepository.findById(id).orElseThrow(()->new CustomException("streetlight not found"));
        if (streetLight.isDeleted()) throw new CustomException("streetlight not found");
        else return streetLight;
    }


}
