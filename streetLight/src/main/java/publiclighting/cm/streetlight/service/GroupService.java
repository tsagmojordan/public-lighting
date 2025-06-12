package publiclighting.cm.streetlight.service;

import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;

public interface GroupService {
    public GroupResponseDto create(GroupDto groupDto);
    StreetLightGroup findById(String groupId) throws CustomException;
    void addChild(Component child,StreetLightGroup group);
}
