package publiclighting.cm.streetlight.service;

import publiclighting.cm.streetlight.vo.Panne;

import java.util.List;

public interface KafkaProducer {
    void transmettrePannesAuServiceMaintenance(List<Panne> pannes);

}
