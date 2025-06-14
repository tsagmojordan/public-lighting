package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;

import java.util.List;

public interface GroupRepository extends JpaRepository<StreetLightGroup, String> {
    @Query("select g from StreetLightGroup g where g.location.zoneName = :zoneName")
    List<StreetLightGroup> findAllByLocation_ZoneName(@Param("zoneName") String zoneName);

    List<Component> findAllByParentId(String id);
}