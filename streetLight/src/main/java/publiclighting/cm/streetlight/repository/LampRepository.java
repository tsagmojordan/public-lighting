package publiclighting.cm.streetlight.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import publiclighting.cm.streetlight.entity.Lamp;
@Repository
public interface LampRepository extends JpaRepository<Lamp,String> {
}
