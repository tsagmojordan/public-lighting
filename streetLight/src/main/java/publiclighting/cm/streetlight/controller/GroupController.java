package publiclighting.cm.streetlight.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import publiclighting.cm.streetlight.dto.ChildrenOfGroupResponseDto;
import publiclighting.cm.streetlight.dto.ComponentDto;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.service.GroupServiceImpl;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private final GroupServiceImpl groupService;

    @PostMapping("/group")
    public GroupResponseDto createGroup(@Valid GroupDto groupDto ) {
        log.info(Constant.LOG_DECORATION+"Controller has well received instruction of creation of a StreetLightGroup"+Constant.LOG_DECORATION);
        return groupService.create(groupDto);
    }

    @PostMapping("/group/child")
    public void addChildToGroup(ComponentDto child, String groupId) throws CustomException {
        log.info(Constant.LOG_DECORATION+"Controller has well received instruction of adding child to group :{}",groupId+Constant.LOG_DECORATION);
        groupService.addChild(child,groupId);
    }
    @GetMapping("/group/{id}/children")
    public List<ChildrenOfGroupResponseDto> getGroupChildren(@PathVariable String id) throws CustomException {
        log.info(Constant.LOG_DECORATION+"Controller has well received instruction of fetching children of group :{}",id+Constant.LOG_DECORATION);
        return groupService.getChildren(id);
    }

    @GetMapping("/groups")
    public List<GroupResponseDto> getGroups() throws CustomException {
        return groupService.getAllGroups();

    }

    @GetMapping("/groups/zone/{zone}")
    public List<GroupResponseDto> getGroupsByZone(@PathVariable String zone) throws CustomException {
        return groupService.getAllGroupsByZone(zone);
    }
    @GetMapping("/groups/municipality/{municipalityId}")
    public List<GroupResponseDto> getGroupsOfMunicipality(@PathVariable Long municipalityId) throws CustomException {
        return groupService.getAllGroupsByMunicipality(municipalityId);
    }


    @GetMapping("/groups/region/{regionId}")
    public List<GroupResponseDto> getGroupsRegion(@PathVariable Long regionId) throws CustomException {
        return groupService.getAllGroupsByRegion(regionId);
    }







}
