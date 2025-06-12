package publiclighting.cm.streetlight.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.service.GroupService;
import publiclighting.cm.streetlight.service.GroupServiceImpl;
import publiclighting.cm.streetlight.service.StreetLightService;
import publiclighting.cm.streetlight.service.StreetLightServiceImpl;
import publiclighting.cm.streetlight.utils.Constant;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class StreetLightController {

    private final GroupService groupService;
    private final StreetLightService streetLightService;


    @PostMapping("/streetLight/{groupId}")
    public StreetLightResponseDto createStreetLight(LampDto lampDto, StreetLightDto streetLightDto, @PathVariable String groupId) throws CustomException {
        log.info(Constant.LOG_DECORATION +"controller has received instruction of creation of a StreetLight"+Constant.LOG_DECORATION);
        return streetLightService.create(lampDto,streetLightDto,groupId);
    }
}
