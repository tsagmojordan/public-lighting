package publiclighting.cm.streetlight.service;

import publiclighting.cm.streetlight.dto.ChildrenResponseDto;
import publiclighting.cm.streetlight.dto.ComponentDto;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;

import java.util.List;

public interface GroupService {
    GroupResponseDto create(GroupDto groupDto) throws CustomException;
    StreetLightGroup findById(String groupId) throws CustomException;
    void addChild(Component child, StreetLightGroup group) throws CustomException;
    void addChild(ComponentDto child, String groupId) throws CustomException;

    List<ChildrenResponseDto>  getAllGroups() throws CustomException;

    List<ChildrenResponseDto>  getAllGroupsByZone(String zoneName) throws CustomException;

    List<ChildrenResponseDto> getAllGroupsByMunicipality(Long municipalityId) throws CustomException;

    List<ChildrenResponseDto>  getAllGroupsByRegion(Long regionId) throws CustomException;

    ChildrenResponseDto findGroup(Long id) throws CustomException;

    GroupResponseDto createSubgroup(GroupDto child, Long groupId) throws CustomException;
}
