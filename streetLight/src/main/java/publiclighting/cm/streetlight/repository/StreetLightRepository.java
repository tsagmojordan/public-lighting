package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLight;

import java.util.List;

@EnableJpaRepositories
public interface StreetLightRepository extends JpaRepository<StreetLight, Long> {
    List<Component> findAllByParentId(String id);
    List<StreetLight> findAllStreetLightsByParentId(String id);
}
