package publiclighting.cm.streetlight.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import publiclighting.cm.streetlight.dto.*;
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

    @Operation(method = "createGroup", summary = "this end point is for creation of a new group", responses = {})
    @PostMapping("/group")
    public GroupResponseDto createGroup(@RequestBody GroupDto groupDto) throws CustomException {
        log.info(Constant.LOG_DECORATION + "Controller has well received instruction of creation of a StreetLightGroup" + Constant.LOG_DECORATION);
        return groupService.create(groupDto);
    }


    @PostMapping("/group/{groupId}/subgroup")
    public GroupResponseDto createSubgroup(@RequestBody GroupDto child, @PathVariable Long groupId) throws CustomException {
        log.info(Constant.LOG_DECORATION + "Controller has well received instruction of adding child to group :{}" + Constant.LOG_DECORATION, groupId);
        return groupService.createSubgroup(child, groupId);
    }
//
//    @DeleteMapping("/group/{groupId}/delete/child/{childId}")
//    public void deleteChildFromGroup(@PathVariable String childId, String groupId) throws CustomException {
//
//    }
//
//    @GetMapping("/group/{id}/children")
//    public List<ChildrenOfGroupResponseDto> getGroupChildren(@PathVariable String id) throws CustomException {
//        log.info(Constant.LOG_DECORATION+"Controller has well received instruction of fetching children of group :{}",id+Constant.LOG_DECORATION);
//        return groupService.getChildren(id);
//    }

    @GetMapping("/groups")
    public List<ChildrenResponseDto>  getGroups() throws CustomException {
        return groupService.getAllGroups();

    }

    @Operation(method = "findGroup", summary = "this method allow you to find a group zoneName")
    @GetMapping("/groups/zone/{zone}")
    public List<ChildrenResponseDto> getGroupsByZone(@PathVariable String zone) throws CustomException {
        return groupService.getAllGroupsByZone(zone);
    }

    @Operation(method = "findGroup", summary = "this method allow you to find a group id")
    @GetMapping("/group/{id}")
    public ChildrenResponseDto findGroup(@PathVariable Long id) throws CustomException {
        return groupService.findGroup(id);
    }


    @GetMapping("/groups/municipality/{municipalityId}")
    public List<ChildrenResponseDto> getGroupsOfMunicipality(@PathVariable Long municipalityId) throws CustomException {
        return groupService.getAllGroupsByMunicipality(municipalityId);
    }


    @GetMapping("/groups/region/{regionId}")
    public List<ChildrenResponseDto>  getGroupsRegion(@PathVariable Long regionId) throws CustomException {
        return groupService.getAllGroupsByRegion(regionId);
    }



}
