package publiclighting.cm.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import publiclighting.cm.auth.entity.Role;
import publiclighting.cm.auth.repository.RoleRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class AuthApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Role role = new Role();
        role.setName("MINEE");
        role.setId(1L);
        roleRepository.save(role);
        role.setName("ADMIN");
        role.setId(2L);
        roleRepository.save(role);
        role.setName("ROLE_USER");
        role.setId(3L);
        roleRepository.save(role);
        role.setName("MUNICIPALITY");
        role.setId(4L);
        roleRepository.save(role);
        role.setName("MAINTAINER");
        role.setId(5L);
        role.setName("SUPERADMIN");
        role.setId(6L);
        roleRepository.save(role);
    }
}