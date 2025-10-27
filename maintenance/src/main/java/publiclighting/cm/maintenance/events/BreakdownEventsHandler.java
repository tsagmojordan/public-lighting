package publiclighting.cm.maintenance.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import publiclighting.cm.maintenance.Service.MaintenanceUseCase;
import publiclighting.cm.maintenance.entity.vo.Maintenance;
import publiclighting.cm.maintenance.entity.vo.Panne;

@Component
@RequiredArgsConstructor
@Slf4j

public class BreakdownEventsHandler {

private final MaintenanceUseCase maintenanceUseCase;


    @KafkaListener(
            topics = "${kafka.topic.maintenance}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeNotification(
            @Payload Panne breakdownIncoming,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment) {

        try {
            Maintenance maintenance = maintenanceUseCase.planifierMaintenance(breakdownIncoming);


            acknowledgment.acknowledge();

            log.info("Panne traitée avec succès: ");

        } catch (Exception e) {
            log.error("Erreur lors du traitement de la notification: {}"
                    ,e);
            // Ne pas acknowledger en cas d'erreur pour retry
            // Ou implémenter une logique de Dead Letter Queue
        }
    }
}
