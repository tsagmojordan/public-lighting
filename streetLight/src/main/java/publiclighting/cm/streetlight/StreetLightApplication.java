package publiclighting.cm.streetlight;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import publiclighting.cm.streetlight.entity.ClassForTime;
import publiclighting.cm.streetlight.entity.LightingProfile;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.enums.Ecolor;
import publiclighting.cm.streetlight.enums.LightingProfileType;
import publiclighting.cm.streetlight.repository.GroupRepository;
import publiclighting.cm.streetlight.repository.LightingProfileRepository;
import publiclighting.cm.streetlight.service.GroupServiceImpl;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class StreetLightApplication implements CommandLineRunner {

    private final LightingProfileRepository lightingProfileRepository;
    private final GroupRepository groupRepository;

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
                .id("21dsfd5f46sdfsfsdf-5654dsftg-5645gdsfg")
                .build());
    }

}
