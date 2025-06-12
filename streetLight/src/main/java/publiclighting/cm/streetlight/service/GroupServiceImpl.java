package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService{


    private final GroupRepository groupRepository;
    private final LightingProfileRepository lightingProfileRepository;


    public GroupResponseDto create(GroupDto groupDto) {
        log.info("==============starting creation of a StreetLightGroup==============");
        log.info("==============groupDto: {}==============", groupDto);
        log.info("==============assigning  default lightingProfileType==============");
        LightingProfile lightingProfile = lightingProfileRepository.findByLightingProfileType(LightingProfileType.DEFAULT);
        log.info("==============lightingProfile: {}==============", lightingProfile);
        log.info("==============constructing group entity==============");
        List<Component> children=new ArrayList<>();
        StreetLightGroup group = StreetLightGroup.groupBuilder()
                .id(UUID.randomUUID().toString())
                .lightingProfile(lightingProfile)
                .zoneName(groupDto.getZoneName())
                .dataOf(StreetLightGroup.class.getName())
                .municipalityId(groupDto.getMunicipalityId())
                .children(children)
                .build();
        log.info("==============group entity: {}==============", group);
        groupRepository.save(group);
        log.info("==============group entity saved==============");


        //send notification trough broker---------------------------------------------------------------------
        //sen group into broker--------------------------------------------------------------


        return GroupResponseDto.builder()
                .id(group.getId())
                .lightingProfileType(group.getLightingProfile().getLightingProfileType())
                .zoneName(group.getZoneName())
                .municipalityId(group.getMunicipalityId())
                .children(group.getChildren())
                .build();
    }

    @Override
    public StreetLightGroup findById(String groupId) throws CustomException {

        if (this.groupRepository.findById(groupId).isPresent()) {
            return groupRepository.findById(groupId).get();
        } else {
            throw new CustomException(Constant.NOT_FOUND);
        }

    }

    @Override
    public void addChild(Component child, StreetLightGroup group) {
        List<Component> children = group.getChildren();
        if (children == null) {
            children = new ArrayList<>();
            children.add(child);
            group.setChildren(children);
        }else{
            children.add(child);
        }
        groupRepository.save(group);
    }
}
