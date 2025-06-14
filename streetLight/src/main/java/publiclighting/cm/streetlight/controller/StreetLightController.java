package publiclighting.cm.streetlight.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.service.GroupService;
import publiclighting.cm.streetlight.service.StreetLightService;
import publiclighting.cm.streetlight.utils.Constant;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j

public class StreetLightController {

    private final GroupService groupService;
    private final StreetLightService streetLightService;


    @Operation(description = "this method allow user to add a new streetLight")
    @PostMapping("/streetLight/{groupId}")
    public StreetLightResponseDto addStreetLight(LampDto lampDto, StreetLightDto streetLightDto, @PathVariable String groupId) throws CustomException {
        log.info(Constant.LOG_DECORATION +"controller has received instruction of creation of a StreetLight"+Constant.LOG_DECORATION);
        return streetLightService.create(lampDto,streetLightDto,groupId);
    }
}
