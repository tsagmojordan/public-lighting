package publiclighting.cm.streetlight;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import publiclighting.cm.streetlight.dto.GroupDto;
import publiclighting.cm.streetlight.entity.ClassForTime;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.entity.Location;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.enums.Ecolor;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;
import publiclighting.cm.streetlight.service.GroupServiceImpl;

import java.util.UUID;


@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class StreetLightApplication implements CommandLineRunner {

    private final LightingProfileRepository lightingProfileRepository;
    private final GroupRepository groupRepository;
    private final GroupServiceImpl groupService;

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
        groupRepository.save(StreetLightGroup.groupBuilder()
                        .id("21diff5f46sdfsfsdf-5654dist-5645diff")
                        .lightingProfile(lightingProfileRepository.getOne(1L))
                        .location(Location.builder().id(UUID.randomUUID().toString())
                                .description("allant de banane a carrefour du chef")
                                .regionId(1L)
                                .departmentId(1L)
                                .arrondissementId(1L)
                                .municipalityId(1L)
                                .zoneName("mendong-carrefour banane")
                                .build())
                .build());
        System.out.println(groupRepository.findAllByLocation_ZoneName("mendong-carrefour banane").toString());

    }

}
