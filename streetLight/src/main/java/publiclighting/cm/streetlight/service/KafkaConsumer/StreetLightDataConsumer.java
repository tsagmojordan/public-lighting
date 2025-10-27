package publiclighting.cm.streetlight.service.KafkaConsumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import publiclighting.cm.streetlight.entity.StreetLightData;
import publiclighting.cm.streetlight.repository.StreetLightDataRepository;
import publiclighting.cm.streetlight.service.StreetLightService;
import publiclighting.cm.streetlight.vo.DataIncoming;
import publiclighting.cm.streetlight.vo.Panne;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j

public class StreetLightDataConsumer {

    private final StreetLightService streetLightService;
    private final StreetLightDataRepository streetLightDataRepository;
    private final KafkaTemplate<String, Panne> streetLightDataKafkaTemplate;


    @KafkaListener(
            topics = "${kafka.topic.notification}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeNotification(
            @Payload DataIncoming dataIncoming,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment) {

        try {
            List<Panne> pannes = streetLightService.verifierPanne(dataIncoming);
            log.info("Incoming data: {}", dataIncoming.toString());
            log.info("Pannes detected: {}", pannes.toString());
            StreetLightData streetLightData = StreetLightData.builder()
                    .isLightOn(dataIncoming.isLightOn())
                    .dataCollectedTime(dataIncoming.dataCollectedTime())
                    .inclination(dataIncoming.inclination())
                    .voltage(dataIncoming.voltage())
                    .serialNumber(dataIncoming.serialNumber())
                    .tension(dataIncoming.tension())
                    .numberOfPresenceDetected(dataIncoming.numberOfPresenceDetected())
                    .build();
            if (pannes.size() == 0) {
                log.info("No pannes found for data collected");
                log.error("saving data into database");
                streetLightDataRepository.save(streetLightData);
            }else {
                log.info("Found pannes for data collected");
                log.info("Sending data to maintenance service");
                for(Panne panne : pannes) {
                    streetLightDataKafkaTemplate.send("maintenance-events","post",panne);
                }
                log.info("saving data into database");
                streetLightDataRepository.save(streetLightData);
            }
            acknowledgment.acknowledge();

            log.info("Notification traitée avec succès: ");

        } catch (Exception e) {
            log.error("Erreur lors du traitement de la notification: {}"
                    ,e);
            // Ne pas acknowledger en cas d'erreur pour retry
            // Ou implémenter une logique de Dead Letter Queue
        }
    }
}
