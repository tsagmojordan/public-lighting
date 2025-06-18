package publiclighting.cm.streetlight;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.entity.*;
import publiclighting.cm.streetlight.enums.Ecolor;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.enums.State;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;
import publiclighting.cm.streetlight.repository.LocationRepository;
import publiclighting.cm.streetlight.repository.StreetLightRepository;
import publiclighting.cm.streetlight.service.GroupServiceImpl;
import publiclighting.cm.streetlight.service.LampService;
import publiclighting.cm.streetlight.service.StreetLightService;

import java.util.UUID;


@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class StreetLightApplication implements CommandLineRunner {

    private final LightingProfileRepository lightingProfileRepository;
    private final GroupRepository groupRepository;
    private final GroupServiceImpl groupService;
    private final LocationRepository locationRepository;
    private final StreetLightService streetLightService;
    private final LampService lampService;
    private final StreetLightRepository streetLightRepository;

    public static void main(String[] args) {
        SpringApplication.run(StreetLightApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        lightingProfileRepository.save(LightingProfile.builder().lightingColor(Ecolor.WHITE)
                .energySavingStartTime(new ClassForTime(22,30,30))
                .lightingProfileType(LightingProfileType.DEFAULT)
                .build());
        lightingProfileRepository.save(LightingProfile.builder().lightingColor(Ecolor.YELLOW)
                .energySavingStartTime(new ClassForTime(00,00,30))
                .lightingProfileType(LightingProfileType.FESTIVE)

                .build());
        Location location=Location.builder()
                .description("mendong-carrefour banane")
                .municipalityId(1L)
                .regionId(1L)
                .departmentId(1L)
                .arrondissementId(1L)
                .zoneName("mendong")
                .build();
        locationRepository.save(location);
        Location location2=Location.builder()
                .description("mendong-carrefour allant jusqu'a abega")
                .municipalityId(1L)
                .regionId(1L)
                .departmentId(1L)
                .arrondissementId(1L)
                .zoneName("mendong")
                .build();
        locationRepository.save(location2);

//        groupRepository.save(StreetLightGroup.groupBuilder()
//                        .lightingProfile(lightingProfileRepository.getOne(1L))
//                        .location(locationRepository.getOne(1L))
//                        .entityName("StreetLightGroup")
//                .build());
//        groupRepository.save(StreetLightGroup.groupBuilder()
//                .lightingProfile(lightingProfileRepository.getOne(1L))
//                .location(locationRepository.getOne(2L))
//                .entityName("StreetLightGroup")
//                .build());
//
//
//
    }

}
