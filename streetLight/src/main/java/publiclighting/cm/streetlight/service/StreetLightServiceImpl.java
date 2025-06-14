package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.Lamp;
import publiclighting.cm.streetlight.entity.StreetLight;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.LampRepository;
import publiclighting.cm.streetlight.repository.StreetLightRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StreetLightServiceImpl implements StreetLightService {

    private final StreetLightRepository streetLightRepository;
    private final GroupService groupService;
    private final LampRepository lampRepository;

    @Transactional
    public StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, String streetLightGroup) throws CustomException {
        log.info(Constant.LOG_DECORATION + "fetch group from database" + Constant.LOG_DECORATION);
        StreetLightGroup group = groupService.findById(streetLightGroup);
        StreetLight streetLight = null;
        log.info(Constant.LOG_DECORATION + "group fetched" + Constant.LOG_DECORATION);
        log.info(Constant.LOG_DECORATION + "lampDto data :{}" + Constant.LOG_DECORATION, lampDto);
        log.info(Constant.LOG_DECORATION + "creating and saving lamp of streetlight" + Constant.LOG_DECORATION);
        Lamp lamp = Lamp.builder()
                .id(UUID.randomUUID().toString())
                .lampType(lampDto.getLampType())
                .lifeDuration(lampDto.getLifeDuration())
                .power(lampDto.getPower())
                .price(lampDto.getPrice())
                .build();
        lampRepository.save(lamp);
        if (lampRepository.findById(lamp.getId()).isPresent()) {
            lamp = lampRepository.findById(lamp.getId()).get();
            log.info(Constant.LOG_DECORATION + "lamp saved" + Constant.LOG_DECORATION);
            log.info(Constant.LOG_DECORATION + "creating streetLight" + Constant.LOG_DECORATION);
            streetLight = StreetLight.streetLightBuilder().lightingProfile(group.getLightingProfile())//setting group ligthing profile to streetLight
                    .lamp(lamp)
                    .streetLightId(UUID.randomUUID().toString())
                    .zoneName(group.getZoneName())
                    .municipalityId(group.getMunicipalityId())
                    .height(streetLightDto.getHeight())
                    .longitude(streetLightDto.getLongitude())
                    .latitude(streetLightDto.getLatitude())
                    .serialNumber(streetLightDto.getSerialNumber())
                    .isMaster(streetLightDto.isMaster())
                    .group(group.getId())
                    .dateOf(StreetLight.class.getName())
                    .build();
            log.info(Constant.LOG_DECORATION + "streetLight created" + Constant.LOG_DECORATION);
            log.info(Constant.LOG_DECORATION + "adding streetLight to group" + Constant.LOG_DECORATION);
            groupService.addChild(streetLight, group);
            log.info(Constant.LOG_DECORATION + "streetLight added to group" + Constant.LOG_DECORATION);
            log.info(Constant.LOG_DECORATION + "saving streetLight" + Constant.LOG_DECORATION);
            streetLightRepository.save(streetLight);
            log.info(Constant.LOG_DECORATION + "StreetLight saved with data :{}" + Constant.LOG_DECORATION, streetLight);
        }

        assert streetLight != null;
        return StreetLightResponseDto.builder()
                .groupId(group.getId())
                .zoneName(group.getZoneName())
                .municipalityId(group.getMunicipalityId())
                .lightingProfile(group.getLightingProfile())
                .streetlightId(streetLight.getStreetLightId())
                .serialNumber(streetLight.getSerialNumber())
                .height(streetLight.getHeight())
                .longitude(streetLight.getLongitude())
                .latitude(streetLight.getLongitude())
                .lampType(streetLight.getLamp().getLampType())
                .build();
    }

    @Override
    public Component findById(String componentId) {
        return streetLightRepository.findById(componentId).orElseThrow();
    }


}
