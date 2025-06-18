package publiclighting.cm.streetlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import publiclighting.cm.streetlight.dto.ChildrenResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLightGroup;

import java.util.List;

public interface GroupRepository extends JpaRepository<StreetLightGroup, Long> {
    @Query("select g from StreetLightGroup g where g.location.zoneName = :zoneName")
    List<StreetLightGroup> findAllByLocation_ZoneName(@Param("zoneName") String zoneName);

//    @Query("select g from StreetLightGroup g where g.")
    List<Component> findAllByParentId(String id);

    List<StreetLightGroup> id(Long id);

    @Query("select g from StreetLightGroup g where g.location.municipalityId =:id")
    List<StreetLightGroup> findAllByMunicipalityId(@Param("id") Long id);

    @Query("select g from StreetLightGroup g where g.location.arrondissementId =:id")
    List<StreetLightGroup> findAllByArrondissementId(@Param("id") Long id);

    @Query("select g from StreetLightGroup g where g.location.departmentId =:id")
    List<StreetLightGroup> findAllByDepartmentId(@Param("id") Long id);

    @Query("select g from StreetLightGroup g where g.location.regionId =:id")
    List<StreetLightGroup> findAllByRegionId(@Param("id") Long id);
    
}