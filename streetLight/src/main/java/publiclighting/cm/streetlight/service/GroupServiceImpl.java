package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.ComponentDto;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service

@RequiredArgsConstructor
@Slf4j

public class GroupServiceImpl implements GroupService {


    private final GroupRepository groupRepository;
    private final LocationService locationService;
    private final LightingProfileService lightingProfileService;
    private final StreetLightService streetLightService;

    @Transactional
    @Override
    public GroupResponseDto create(GroupDto groupDto) throws CustomException {
        log.info(Constant.LOG_DECORATION + "Creating group with data: {}" + Constant.LOG_DECORATION, groupDto);
        Location location = locationService.createLocation(groupDto.getLocation());
        LightingProfile lightingProfile = lightingProfileService.getDefaultLightingProfile();
        StreetLightGroup streetLightGroup = StreetLightGroup.groupBuilder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .updateAt(new Date())
                .hasChildren(false)
                .hasSubgroup(false)
                .isDeleted(false)
                .build();
        streetLightGroup.setEntityName("StreetLightGroup");
        streetLightGroup.setLightingProfile(lightingProfile);
        streetLightGroup.setLocation(location);
        streetLightGroup.setParentId("");
        log.info(Constant.LOG_DECORATION + "new group have data: {} " + Constant.LOG_DECORATION +
                "\n " + Constant.LOG_DECORATION + "now we are saving" + Constant.LOG_DECORATION, groupDto);
        try {
            groupRepository.save(streetLightGroup);
            log.info(Constant.LOG_DECORATION + "group saved successfully" + Constant.LOG_DECORATION);
        } catch (Exception e) {
            log.error(Constant.LOG_DECORATION + "group save failed due to {}", e.getMessage() + Constant.LOG_DECORATION);
            throw new CustomException(Constant.WRONG);
        }

        return GroupResponseDto.builder()
                .id(streetLightGroup.getId())
                .zoneName(groupDto.getLocation().getZoneName())
                .communeId(groupDto.getLocation().getMunicipalityId())
                .lightingProfileType(lightingProfileService.getDefaultLightingProfile().getLightingProfileType())
                .children(null)
                .build();
    }

    @Override
    public StreetLightGroup findById(String groupId) throws CustomException {
        return null;
    }

    @Override
    public void addChild(Component child, StreetLightGroup group) throws CustomException {

    }

    @Override
    public void addChild(ComponentDto child, String groupId) throws CustomException {

    }


    @Override
    public List<GroupResponseDto> getAllGroups() {
        return List.of();
    }

    @Override
    public List<GroupResponseDto> getAllGroupsByZone(String zoneName) throws CustomException {
        return List.of();
    }

    @Override
    public List<GroupResponseDto> getAllGroupsByMunicipality(Long municipalityId) {
        return List.of();
    }

    @Override
    public List<GroupResponseDto> getAllGroupsByRegion(Long regionId) {
        return List.of();
    }

    @Override
    public GroupResponseDto findGroup(String id) throws CustomException {
        StreetLightGroup group = groupRepository.findById(id).orElseThrow();
        List<Component> children = group.getChildren();
        if (group.isDeleted()) throw new CustomException("this group doesn't exist");

        if (group.isHasChildren()) {
            List<Component> streetLightChildren = streetLightService.findAllByGroup(id);
            streetLightChildren.stream()
                    .filter(c -> !c.isDeleted())
                    .map(children::add)
                    .toList();

        }
        if (group.isHasSubgroup()) {
            groupRepository.findAllByParentId(id);
            List<Component> subgroups = groupRepository.findAllByParentId(id);
            subgroups.stream()
                    .filter(c -> !c.isDeleted())
                    .map(children::add)
                    .toList();
        }
        return GroupResponseDto.builder()
                .zoneName(group.getLocation().getZoneName())
                .communeId(group.getLocation().getMunicipalityId())
                .lightingProfileType(lightingProfileService.getDefaultLightingProfile().getLightingProfileType())
                .children(children)
                .build();
    }
}
