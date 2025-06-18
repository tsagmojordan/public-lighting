package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.*;
import publiclighting.cm.streetlight.entity.*;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
                .createAt(new Date())
                .updateAt(new Date())
                .hasChildren(false)
                .hasSubgroup(false)
                .isDeleted(false)
                .build();

        // streetLightGroup.setId(UUID.randomUUID().toString());
        streetLightGroup.setEntityName("StreetLightGroup");
        streetLightGroup.setLightingProfile(lightingProfile);
        streetLightGroup.setLocation(location);
        streetLightGroup.setParentId("/");
        log.info(Constant.LOG_DECORATION + "new group have data: {} " + Constant.LOG_DECORATION +
                "\n " + Constant.LOG_DECORATION + "now we are saving" + Constant.LOG_DECORATION, groupDto);
        try {

            groupRepository.save(streetLightGroup);

            log.info(Constant.LOG_DECORATION + "group saved successfully" + Constant.LOG_DECORATION);
        } catch (Exception e) {
            log.error(Constant.LOG_DECORATION + "group save failed due to {}", e.getMessage() + Constant.LOG_DECORATION);
            e.getMessage();
        }

        return GroupResponseDto.builder()
                .id(streetLightGroup.getId())
                .zoneName(groupDto.getLocation().getZoneName())
                .communeId(groupDto.getLocation().getMunicipalityId())
                .lightingProfileType(lightingProfile.getLightingProfileType())
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
    public List<ChildrenResponseDto> getAllGroups() throws CustomException {
        List<StreetLightGroup> groups =groupRepository.findAll();
        return getChildrenResponseDtos(groups);
    }

    @Override
    public List<ChildrenResponseDto> getAllGroupsByZone(String zoneName) throws CustomException {
        List<StreetLightGroup> groups =groupRepository.findAllByLocation_ZoneName(zoneName);
        return getChildrenResponseDtos(groups);
    }

    private List<ChildrenResponseDto> getChildrenResponseDtos(List<StreetLightGroup> groups) throws CustomException {
        if (groups.isEmpty()){
            throw new CustomException(Constant.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        List<ChildrenResponseDto> response=new ArrayList<>();
        for (StreetLightGroup streetLightGroup : groups) {
            response.add(
                    findGroup(streetLightGroup.getId())
            );
        }
        return response;
    }


    @Override
    public  List<ChildrenResponseDto> getAllGroupsByMunicipality(Long municipalityId) throws CustomException {
        List<StreetLightGroup> groups =groupRepository.findAllByMunicipalityId(municipalityId);
        return getChildrenResponseDtos(groups);
    }

    @Override
    public List<ChildrenResponseDto> getAllGroupsByRegion(Long regionId) throws CustomException {
        List<StreetLightGroup> groups =groupRepository.findAllByRegionId(regionId);
        return getChildrenResponseDtos(groups);
    }


    @Override
    public ChildrenResponseDto findGroup(Long id) throws CustomException {
        StreetLightGroup group = groupRepository.findById(id).orElseThrow();
        ComponentDto componentDto = new ComponentDto();


        if (group.isDeleted()) throw new CustomException("this group doesn't exist");

        if (group.isHasChildren()) {
            componentDto.getStreetLightResponseDtos().addAll(streetLightService.findAllByGroup(id).stream()
                    .map(
                            component -> {
                                try {
                                    StreetLight streetLight = streetLightService.findById(component.getId());
                                    return ReduiceStreetLightResponseDto.builder()
                                            .streetlightId(streetLight.getId())
                                            .isMaster(streetLight.isMaster())
                                            .gpsPosition(PositionDto.builder()
                                                    .hauteur(streetLight.getGpsPosition().getHauteur())
                                                    .latitude(streetLight.getGpsPosition().getLatitude())
                                                    .longitude(streetLight.getGpsPosition().getLongitude())
                                                    .build()
                                            )
                                            .state(streetLight.getState())
                                            .lampType(streetLight.getLamp().getLampType())
                                            .serialNumber(streetLight.getSerialNumber())
                                            .serialNumber(streetLight.getSerialNumber())
                                            .build();
                                } catch (CustomException e) {
                                    try {
                                        throw new CustomException("failed to fetch streetlight data of this group", HttpStatus.NO_CONTENT);
                                    } catch (CustomException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }

                            }
                    )
                    .toList()
            );

        }

        if (group.isHasSubgroup()) {
            groupRepository.findAllByParentId(id.toString());
            List<Component> subgroups;
            componentDto.getGroupResponseDtos().addAll(groupRepository.findAllByParentId(id.toString()).stream()
                    .filter(c -> !c.isDeleted())
                    .map(
                            component -> GroupResponseDto.builder()
                                    .id(component.getId())
                                    .lightingProfileType(component.getLightingProfile().getLightingProfileType())
                                    .communeId(component.getLocation().getMunicipalityId())
                                    .zoneName(component.getLocation().getZoneName())
                                    .build()

                    ).toList()
            );
        }
        return ChildrenResponseDto.builder()
                .parentId(group.getParentId())
                .parentZoneName(group.getLocation().getZoneName())
                .parentCommuneId(group.getLocation().getMunicipalityId().toString())
                .children(componentDto)
                .build();
    }

    @Override
    public GroupResponseDto createSubgroup(GroupDto child, Long groupId) throws CustomException {
        if (findGroup((groupId)) == null) {
            throw new CustomException("parent group doesn't exist");
        }
        StreetLightGroup parent = groupRepository.findById(groupId).orElseThrow(() -> new CustomException("parent group doesn't exist", HttpStatus.NOT_FOUND));//fetch parent
        if (parent.getEntityName().equals("StreetLightGroup")) {//check if parent is a group


            GroupResponseDto groupResponse = create(child);//create the group
            StreetLightGroup group = groupRepository.findById(groupResponse.getId()).orElseThrow(() -> new CustomException("group doesn't exist", HttpStatus.NOT_FOUND));//fetch the new group to be sure that the group has been created
            parent.setHasSubgroup(true);
            group.setParentId(groupId.toString());
            groupRepository.save(parent);
            groupRepository.save(group);
            return groupResponse;
        } else {
            throw new CustomException("parent group is not a group", HttpStatus.CONFLICT);
        }
    }
}
