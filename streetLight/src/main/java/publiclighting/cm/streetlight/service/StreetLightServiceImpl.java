package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
import java.util.stream.Collectors;

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
    public StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, Long streetLightGroup) throws CustomException {
        Lamp lamp = lampService.createLamp(lampDto);
        double serialNumberGen = Math.round(Math.random() * 10000000000000000L) ;
        GpsPosition gpsPosition = GpsPosition.builder()
                .hauteur(streetLightDto.getGpsPosition().getHauteur())
                .longitude(streetLightDto.getGpsPosition().getLongitude())
                .latitude(streetLightDto.getGpsPosition().getLatitude())
                .build();
        log.info(Constant.LOG_DECORATION+"saving gps position"+Constant.LOG_DECORATION );

        gpsRepository.save(gpsPosition);
        log.info(Constant.LOG_DECORATION+"position saved"+Constant.LOG_DECORATION );

        StreetLightGroup group = groupRepository.findById(streetLightGroup).orElseThrow(() -> new CustomException(Constant.LOG_DECORATION + "group doesn't exist" + Constant.LOG_DECORATION, null));
        Location location = group.getLocation();
        LightingProfile lightingProfile = lightingProfileRepository.findById(group.getLightingProfile().getId()).orElseThrow();
        StreetLight streetLight = StreetLight.streetLightBuilder()
                .groupId(group.getId().toString())
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
        streetLight.setSerialNumber("STREETLIGHT-" + serialNumberGen);
        streetLight.setState(State.OFF);
        log.info(Constant.LOG_DECORATION+" saving streetlight: {}"+Constant.LOG_DECORATION ,streetLight);

        group.setHasChildren(true);


        //if there is a master streetlight node in the group, we will return a conflict situation

          log.info(Constant.LOG_DECORATION+"fetching master node in parent group"+Constant.LOG_DECORATION );
            List<StreetLight> streetLights = findAllByParentId(group.getId()).stream()
                    .filter(s -> s.isMaster())
                    .toList();
            log.info(Constant.LOG_DECORATION+"list of master in the group{}"+Constant.LOG_DECORATION, streetLights);
            log.info(Constant.LOG_DECORATION+"checking if the group have master streetlights"+Constant.LOG_DECORATION );
            switch (streetLights.isEmpty()){
                case true -> {
                    log.info(Constant.LOG_DECORATION+"group doesn't have master node"+Constant.LOG_DECORATION );
                    if (streetLightDto.isMaster()) {
                        log.info(Constant.LOG_DECORATION+" register a new master node"+Constant.LOG_DECORATION );
                        groupRepository.save(group);
                        streetLightRepository.save(streetLight);
                        log.info(Constant.LOG_DECORATION+"registration done"+Constant.LOG_DECORATION );;
                    }
                    else if (!streetLightDto.isMaster()) {
                        log.info(Constant.LOG_DECORATION+"we want to register a simple node but there is not master node"+Constant.LOG_DECORATION );
                        log.info(Constant.LOG_DECORATION+"exception: a group should have master node"+Constant.LOG_DECORATION );
                        throw new CustomException("create a master first before create simple node", HttpStatus.CONFLICT);
                    }
                }
                case false -> {
                    log.info(Constant.LOG_DECORATION+"there is a master node"+Constant.LOG_DECORATION );

                    if (!streetLightDto.isMaster()) {
                        log.info(Constant.LOG_DECORATION+"we want to register a master simple node"+Constant.LOG_DECORATION );

                        groupRepository.save(group);
                        streetLightRepository.save(streetLight);
                        log.info(Constant.LOG_DECORATION+"registration done"+Constant.LOG_DECORATION );

                    }else {
                        log.info(Constant.LOG_DECORATION+"registration of a master node impossible due to presence of a master node in the group"+Constant.LOG_DECORATION );

                        throw new CustomException("you are trying to assigning a second master node in the same group,a group can have only one master node", HttpStatus.CONFLICT);
                    }

                }
            }


        return StreetLightResponseDto.builder()
                .streetlightId(streetLight.getId())
                .location(streetLightDto.getLocation())
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
    public List<Component> findAllByGroup(Long id) {
        return streetLightRepository.findAllByParentId(id.toString()).stream()
                .filter(c -> !c.isDeleted())
                .toList();
    }

    @Override
    public StreetLight findById(Long id) throws CustomException {
        StreetLight streetLight = streetLightRepository.findById(id).orElseThrow(() -> new CustomException("streetlight not found"));
        if (streetLight.isDeleted()) throw new CustomException("streetlight not found");
        else return streetLight;
    }

    @Override
    public List<StreetLight> findAllByParentId(Long id) throws CustomException {
        return streetLightRepository.findAllStreetLightsByParentId(id.toString()).stream()
                .filter(component -> !component.isDeleted())
                .toList();
    }


}
