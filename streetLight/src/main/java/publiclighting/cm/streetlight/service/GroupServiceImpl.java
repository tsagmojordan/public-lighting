package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.ChildrenOfGroupResponseDto;
import publiclighting.cm.streetlight.dto.ComponentDto;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;
import publiclighting.cm.streetlight.repository.StreetLightRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService{


    private final GroupRepository groupRepository;
    private final LightingProfileRepository lightingProfileRepository;
    private final StreetLightRepository streetLightService;
    private final LocationService locationService;


    public GroupResponseDto create(GroupDto groupDto) {
        log.info("==============starting creation of a StreetLightGroup==============");
        log.info("==============groupDto: {}==============", groupDto);
        log.info("==============assigning  default lightingProfileType==============");
        LightingProfile lightingProfile = lightingProfileRepository.findByLightingProfileType(LightingProfileType.DEFAULT);
        log.info("==============lightingProfile: {}==============", lightingProfile);
        log.info("==============constructing group entity==============");
        List<Component> children=new ArrayList<>();
        Location location= Location.builder().id(UUID.randomUUID().toString())
                .arrondissementId(groupDto.getLocation().getArrondissementId())
                .departmentId(groupDto.getLocation().getDepartmentId())
                .regionId(groupDto.getLocation().getRegionId())
                .description(groupDto.getLocation().getDescription())
                .build();
        locationService.createLocation(location);
        StreetLightGroup group = StreetLightGroup.groupBuilder()
//                .id(UUID.randomUUID().toString())
                .lightingProfile(lightingProfile)
                .location(groupDto.getLocation())
                .dataOf(StreetLightGroup.class.getName())
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
                .location(group.getLocation())
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
    public void addChild(ComponentDto child, String groupId) throws CustomException {
        StreetLightGroup group=findById(groupId);
        Component newChild=findById(child.getComponentId());
        if (child.getComponentId() == null) {
            log.info(Constant.LOG_DECORATION+"child componentId is null : {}", child.getComponentId() + Constant.LOG_DECORATION);
            throw new CustomException(Constant.NOT_FOUND);
        }
        log.info(Constant.LOG_DECORATION+"we are trying to found entity type"+Constant.LOG_DECORATION);
        switch (newChild.getClass().getSimpleName()){

            case "StreetLight": newChild=streetLightService.findById(child.getComponentId()).orElseThrow();break;
            case "StreetLightGroup": newChild=findById(child.getComponentId());break;
            default: throw new CustomException(Constant.NO_ENTITY);
        }
        log.info(Constant.LOG_DECORATION+"new child : {}", newChild + Constant.LOG_DECORATION);
        addChild(newChild,group);
    }

    @Override
    public void addChild(Component child, StreetLightGroup group) throws CustomException {
        log.info(Constant.LOG_DECORATION+"adding child to group : {}", group.getId() + Constant.LOG_DECORATION);
        List<Component> children = group.getChildren();
        log.info(Constant.LOG_DECORATION+"children extract from group : {}", children + Constant.LOG_DECORATION);
        if (children == null) {
            log.info(Constant.LOG_DECORATION+"children are null : {}", children + Constant.LOG_DECORATION);
            children = new ArrayList<>();
            log.info(Constant.LOG_DECORATION+"children initialised ass arraylist : {}", children + Constant.LOG_DECORATION);
            children.add(child);
            log.info(Constant.LOG_DECORATION+"child added to children: {}", children + Constant.LOG_DECORATION);
            group.setChildren(children);
            log.info(Constant.LOG_DECORATION+"children set to group : {}", children + Constant.LOG_DECORATION);
        }else{
//            children.stream()
//                            .filter(child1 -> child1.get)========== we want to avoid it that the same component exists two times in the same hierarchy
            log.info(Constant.LOG_DECORATION+"children are not null : {}", children + Constant.LOG_DECORATION);
            children.add(child);
            log.info(Constant.LOG_DECORATION+"child added to existing children: {}", children + Constant.LOG_DECORATION);
        }
        log.info(Constant.LOG_DECORATION+"saving group with children : {}", group + Constant.LOG_DECORATION);
        groupRepository.save(group);
        log.info(Constant.LOG_DECORATION+"group saved with children : {}", group + Constant.LOG_DECORATION);
    }

    @Override
    public List<ChildrenOfGroupResponseDto> getChildren(String groupId) throws CustomException {
        log.info(Constant.LOG_DECORATION+"fetching children of group : {}", groupId + Constant.LOG_DECORATION);
        List<Component> children = groupRepository.findById(groupId).get().getChildren();
        log.info(Constant.LOG_DECORATION+"children fetched : {}", children + Constant.LOG_DECORATION);
        if (this.groupRepository.findById(groupId).isPresent() && children != null && !children.isEmpty()) {
            log.info(Constant.LOG_DECORATION+"children are not empty : {}", children + Constant.LOG_DECORATION);
            return children.stream().filter(child -> !child.componentIsDeleted())
                    .map(child -> ChildrenOfGroupResponseDto.builder()
                            .componentId(null)
                            .entityName(child.getClass().getSimpleName())
                            .createdAt(child.getCreatedAt())
                            .build())
                    .toList();

        } else {
            log.error(Constant.LOG_DECORATION+"group not found or children are empty : {}", groupId + Constant.LOG_DECORATION);
            throw new CustomException(Constant.NOT_FOUND);
        }

    }

    @Override
    public List<GroupResponseDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .filter(group -> !group.isDeleted())
                .map(group -> GroupResponseDto.builder()
                        .lightingProfileType(group.getLightingProfile().getLightingProfileType())
                        .children(group.getChildren())
                        .location(group.getLocation())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<GroupResponseDto> getAllGroupsByZone(String zoneName) throws CustomException {
        List<StreetLightGroup> groups=groupRepository.findAllByLocation_ZoneName(zoneName);
        if (groups.isEmpty()) {
            throw new CustomException(Constant.NOT_FOUND);
        }else {
            return groups.stream()
                    .filter(group -> !group.isDeleted())
                    .map(group -> GroupResponseDto.builder()
                            .lightingProfileType(group.getLightingProfile().getLightingProfileType())
                            .children(group.getChildren())
                            .location(group.getLocation())
                            .build()
                    ).collect(Collectors.toList());
        }

    }

    @Override
    public List<GroupResponseDto> getAllGroupsByMunicipality(Long municipalityId) {
        return List.of();
    }

    @Override
    public List<GroupResponseDto> getAllGroupsByRegion(Long regionId) {
        return List.of();
    }


}
