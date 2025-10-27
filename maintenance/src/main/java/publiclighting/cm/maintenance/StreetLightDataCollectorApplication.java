package publiclighting.cm.maintenance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreetLightDataCollectorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StreetLightDataCollectorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
