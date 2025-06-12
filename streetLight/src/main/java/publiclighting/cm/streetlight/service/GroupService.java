package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.BaseEntity;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupService {


    private final GroupRepository groupRepository;
    private final LightingProfileRepository lightingProfileRepository;


    public GroupResponseDto create(GroupDto groupDto) {
        log.info("starting creation of a StreetLightGroup");
        log.info("groupDto: {}", groupDto);
        log.info("assigning  default lightingProfileType");
        LightingProfile lightingProfile = lightingProfileRepository.findByLightingProfileType(LightingProfileType.DEFAULT);
        log.info("lightingProfile: {}", lightingProfile);
        log.info("constructing group entity");
        List<Component> children=new ArrayList<>();
        StreetLightGroup group = StreetLightGroup.groupBuilder()
                .id(UUID.randomUUID().toString())
                .lightingProfile(lightingProfile)
                .zoneName(groupDto.getZoneName())
                .dataOf(StreetLightGroup.class.getName())
                .municipalityId(groupDto.getMunicipalityId())
                .children(children)
                .build();
        log.info("group entity: {}", group);
        groupRepository.save(group);
        log.info("save group entity");


        //send notification trough broker---------------------------------------------------------------------


        return GroupResponseDto.builder()
                .id(group.getId())
                .lightingProfileType(group.getLightingProfile().getLightingProfileType())
                .zoneName(group.getZoneName())
                .municipalityId(group.getMunicipalityId())
                .children(group.getChildren())
                .build();
    }
}
