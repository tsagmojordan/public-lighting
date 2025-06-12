package publiclighting.cm.streetlight.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.dto.GroupResponseDto;
import publiclighting.cm.streetlight.service.GroupService;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/group")
    public GroupResponseDto createGroup(@Valid GroupDto groupDto ) {
        log.info("Controller has well received instruction of creation of a StreetLightGroup");
        return groupService.create(groupDto);
    }

}
