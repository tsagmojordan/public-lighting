package publiclighting.cm.streetlight.service;

import publiclighting.cm.streetlight.dto.ChildrenOfGroupResponseDto;
import publiclighting.cm.streetlight.dto.ComponentDto;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;

import java.util.List;

public interface GroupService {
    public GroupResponseDto create(GroupDto groupDto);
    StreetLightGroup findById(String groupId) throws CustomException;
    void addChild(Component child, StreetLightGroup group) throws CustomException;
    void addChild(ComponentDto child, String groupId) throws CustomException;
    List<ChildrenOfGroupResponseDto> getChildren(String groupId) throws CustomException;


    List<GroupResponseDto> getAllGroups();

    List<GroupResponseDto> getAllGroupsByZone(String zoneName) throws CustomException;

    List<GroupResponseDto> getAllGroupsByMunicipality(Long municipalityId);

    List<GroupResponseDto> getAllGroupsByRegion(Long regionId);
}
