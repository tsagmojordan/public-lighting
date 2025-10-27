package publiclighting.cm.maintenance.controler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import publiclighting.cm.maintenance.dto.EquipeDto;
import publiclighting.cm.maintenance.dto.MaintenancierDto;
import publiclighting.cm.maintenance.entity.Equipe;
import publiclighting.cm.maintenance.entity.Maintenancier;
import publiclighting.cm.maintenance.entity.vo.Notification;
import publiclighting.cm.maintenance.repository.EquipeRepository;
import publiclighting.cm.maintenance.repository.MaintenancierRepository;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EquipeController {

    private final KafkaTemplate<String, Notification> equipeKafkaTemplate;
    private final MaintenancierRepository maintenancierRepository;
    private final EquipeRepository equipeRepository;
    @PostMapping("/equipe")
    public ResponseEntity<?> addEquipe(@RequestBody EquipeDto equipe) {
        Equipe equipeEntity = new Equipe();
        equipeEntity.setName(equipe.getName());
        equipeEntity.setCommuneId(equipe.getCommuneId());
        equipeEntity.setCommuneName(equipe.getCommuneName());
        equipeEntity.setMaintenanciers(new ArrayList<>());
        return ResponseEntity.ok().body(equipeRepository.save(equipeEntity));
    }
    @PostMapping("/equipe/maintenancier/{equipeId}")
    @Transactional
    public ResponseEntity<Equipe> addMaintenancier(
            @RequestBody MaintenancierDto maintenancierDto,
            @PathVariable String equipeId) {

        // Find equipe or throw 404
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new
                        ResourceNotFoundException("Equipe not found with id: " + equipeId));

        log.info("Equipe found: {}", equipe);
        log.info("Adding maintenancier: {}", maintenancierDto);

        // Check team size limit
        List<Maintenancier> maintenanciers = equipe.getMaintenanciers();
        if (maintenanciers == null) {
            maintenanciers = new ArrayList<>();
            equipe.setMaintenanciers(maintenanciers);
        }

        if (maintenanciers.size() >= 5) {
            throw new IllegalArgumentException("L'équipe est déjà complète (maximum 5 maintenanciers)");
        }

        // Create maintenancier entity
        Maintenancier maintenancier = Maintenancier.builder()
                .nom(maintenancierDto.getNom())
                .prenom(maintenancierDto.getPrenom())
                .email(maintenancierDto.getEmail())
                .telephone(maintenancierDto.getTelephone())
                .adresse(maintenancierDto.getAdresse())
                .build();

        // Save maintenancier first
        maintenancier = maintenancierRepository.save(maintenancier);

        // Add to equipe's list
        maintenanciers.add(maintenancier);

        // Save equipe (cascade should handle this, but explicit save is safer)
        equipe = equipeRepository.save(equipe);

        // Send notification
        try {
            Notification notification = new Notification(
                    maintenancier.getId(),
                    maintenancier.getEmail(),
                    "Ajout à équipe",
                    "Vous avez été ajouté à l'équipe : " + equipe.getName()
            );
            equipeKafkaTemplate.send("notification-topic", "post", notification);
        } catch (Exception e) {
            log.error("Failed to send notification: {}", e.getMessage());
            // Don't fail the request if notification fails
        }

        return ResponseEntity.ok(equipe);
    }
    @GetMapping("/equipe/{equipeId}")
    public ResponseEntity<?> getEquipe(@PathVariable String equipeId) {
        return ResponseEntity.ok().body(equipeRepository.findById(equipeId).orElseThrow());
    }

    @GetMapping("/equipe")
    public ResponseEntity<?> getEquipe(@RequestBody Maintenancier maintenancier) {
        return ResponseEntity.ok().body(equipeRepository.findByMaintenanciers(maintenancier));
    }



}
