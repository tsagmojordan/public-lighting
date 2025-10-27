package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.entity.*;
import publiclighting.cm.streetlight.enums.State;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.*;
import publiclighting.cm.streetlight.utils.Constant;
import publiclighting.cm.streetlight.vo.DataIncoming;
import publiclighting.cm.streetlight.vo.Panne;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StreetLightServiceImpl implements StreetLightService {


    private final StreetLightRepository streetLightRepository;
    private final LampService lampService;
    private final GroupRepository groupRepository;
    private final GpsRepository gpsRepository;
    private final LightingProfileService lightingProfileService;
    private final LightingProfileRepository lightingProfileRepository;

    @Override
    public StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, Long streetLightGroup) throws CustomException {
        Lamp lamp = lampService.createLamp(lampDto);
        double serialNumberGen = Math.round(Math.random() * 10000000000000000L) ;
        GpsPosition gpsPosition = GpsPosition.builder()
                .hauteur(streetLightDto.getGpsPosition().getHauteur())
                .longitude(streetLightDto.getGpsPosition().getLongitude())
                .latitude(streetLightDto.getGpsPosition().getLatitude())
                .build();
        log.info(Constant.LOG_DECORATION+"saving gps position"+Constant.LOG_DECORATION );

        gpsRepository.save(gpsPosition);
        log.info(Constant.LOG_DECORATION+"position saved"+Constant.LOG_DECORATION );

        StreetLightGroup group = groupRepository.findById(streetLightGroup).orElseThrow(() -> new CustomException(Constant.LOG_DECORATION + "group doesn't exist" + Constant.LOG_DECORATION, null));
        Location location = group.getLocation();
        LightingProfile lightingProfile = lightingProfileRepository.findById(group.getLightingProfile().getId()).orElseThrow();
        StreetLight streetLight = StreetLight.streetLightBuilder()
                .groupId(group.getId().toString())
                .createAt(new Date())
                .isMaster(streetLightDto.isMaster())
                .updateAt(new Date())
                .isDeleted(false)
                .build();
        streetLight.setEntityName("StreetLight");
        streetLight.setGpsPosition(gpsPosition);
        streetLight.setLocation(location);
        streetLight.setLamp(lamp);
        streetLight.setLightingProfile(lightingProfile);
        streetLight.setSerialNumber("STREETLIGHT-" + serialNumberGen);
        streetLight.setState(State.OFF);
        log.info(Constant.LOG_DECORATION+" saving streetlight: {}"+Constant.LOG_DECORATION ,streetLight);

        group.setHasChildren(true);


        //if there is a master streetlight node in the group, we will return a conflict situation

          log.info(Constant.LOG_DECORATION+"fetching master node in parent group"+Constant.LOG_DECORATION );
            List<StreetLight> streetLights = findAllByParentId(group.getId()).stream()
                    .filter(s -> s.isMaster())
                    .toList();
            log.info(Constant.LOG_DECORATION+"list of master in the group{}"+Constant.LOG_DECORATION, streetLights);
            log.info(Constant.LOG_DECORATION+"checking if the group have master streetlights"+Constant.LOG_DECORATION );
            switch (streetLights.isEmpty()){
                case true -> {
                    log.info(Constant.LOG_DECORATION+"group doesn't have master node"+Constant.LOG_DECORATION );
                    if (streetLightDto.isMaster()) {
                        log.info(Constant.LOG_DECORATION+" register a new master node"+Constant.LOG_DECORATION );
                        groupRepository.save(group);
                        streetLightRepository.save(streetLight);
                        log.info(Constant.LOG_DECORATION+"registration done"+Constant.LOG_DECORATION );;
                    }
                    else if (!streetLightDto.isMaster()) {
                        log.info(Constant.LOG_DECORATION+"we want to register a simple node but there is not master node"+Constant.LOG_DECORATION );
                        log.info(Constant.LOG_DECORATION+"exception: a group should have master node"+Constant.LOG_DECORATION );
                        throw new CustomException("create a master first before create simple node", HttpStatus.CONFLICT);
                    }
                }
                case false -> {
                    log.info(Constant.LOG_DECORATION+"there is a master node"+Constant.LOG_DECORATION );

                    if (!streetLightDto.isMaster()) {
                        log.info(Constant.LOG_DECORATION+"we want to register a master simple node"+Constant.LOG_DECORATION );

                        groupRepository.save(group);
                        streetLightRepository.save(streetLight);
                        log.info(Constant.LOG_DECORATION+"registration done"+Constant.LOG_DECORATION );

                    }else {
                        log.info(Constant.LOG_DECORATION+"registration of a master node impossible due to presence of a master node in the group"+Constant.LOG_DECORATION );

                        throw new CustomException("you are trying to assigning a second master node in the same group,a group can have only one master node", HttpStatus.CONFLICT);
                    }

                }
            }


        return StreetLightResponseDto.builder()
                .streetlightId(streetLight.getId())
                .location(streetLightDto.getLocation())
                .gpsPosition(streetLightDto.getGpsPosition())
                .serialNumber(streetLight.getSerialNumber())
                .lampType(lamp.getLampType())
                .zoneName(streetLight.getLocation().getZoneName())
                .isMaster(streetLightDto.isMaster())
                .state(State.OFF)
                .lampType(lamp.getLampType())
                .lightingProfile(streetLight.getLightingProfile().getLightingProfileType())
                .build();
    }

    @Override
    public Component findById(String componentId) {
        return null;
    }

    @Override
    public List<Component> findAllByGroup(Long id) {
        return streetLightRepository.findAllByParentId(id.toString()).stream()
                .filter(c -> !c.isDeleted())
                .toList();
    }

    @Override
    public StreetLight findById(Long id) throws CustomException {
        StreetLight streetLight = streetLightRepository.findById(id).orElseThrow(() -> new CustomException("streetlight not found"));
        if (streetLight.isDeleted()) throw new CustomException("streetlight not found");
        else return streetLight;
    }

    @Override
    public List<StreetLight> findAllByParentId(Long id) throws CustomException {
        return streetLightRepository.findAllStreetLightsByParentId(id.toString()).stream()
                .filter(component -> !component.isDeleted())
                .toList();
    }

    @Override
    public List<Panne> verifierPanne(DataIncoming dataIncoming) {
        List<Panne> pannes = new ArrayList<>();

        log.info("🔍 Vérification des pannes pour le lampadaire [{}]", dataIncoming.serialNumber());

        // 1️⃣ Récupération des informations contextuelles
        StreetLight streetLight = streetLightRepository.findBySerialNumber(dataIncoming.serialNumber());
        if (streetLight == null) {
            log.error("❌ Aucun lampadaire trouvé pour le numéro de série [{}]", dataIncoming.serialNumber());
            return List.of(new Panne(
                    dataIncoming.serialNumber(),
                    dataIncoming.dataCollectedTime(),
                    "Lampadaire inconnu dans le système.",
                    new GpsPosition("Not available",0.0, 0.0,0.0)
            ));
        }

        GpsPosition localisation = streetLight.getGpsPosition();
        LocalDateTime now = dataIncoming.dataCollectedTime();
        LocalTime heure = now.toLocalTime();

        log.debug("🕒 Données reçues à {} pour le lampadaire [{}]", now, dataIncoming.serialNumber());
        log.debug("📍 Localisation GPS : {}", localisation);

        // 2️⃣ Définition des heures normales de fonctionnement (ex. 18h à 6h)
        LocalTime heureDebut = LocalTime.of(18, 0);
        LocalTime heureFin = LocalTime.of(6, 0);
        boolean estHeureDeFonctionnement = (heure.isAfter(heureDebut) || heure.isBefore(heureFin));

        log.debug("⏰ Heure actuelle : {}, période de fonctionnement active : {}", heure, estHeureDeFonctionnement);

        // 3️⃣ Vérification tension d’alimentation
        if (dataIncoming.tension() < 180) {
            log.warn("⚠️ Tension basse détectée : {}V", dataIncoming.tension());
            pannes.add(new Panne(
                    dataIncoming.serialNumber(),
                    now,
                    "Tension d’alimentation trop basse (" + dataIncoming.tension() + "V).",
                    localisation
            ));
        } else if (dataIncoming.tension() > 260) {
            log.warn("⚠️ Tension élevée détectée : {}V", dataIncoming.tension());
            pannes.add(new Panne(
                    dataIncoming.serialNumber(),
                    now,
                    "Tension d’alimentation trop élevée (" + dataIncoming.tension() + "V).",
                    localisation
            ));
        } else {
            log.debug("✅ Tension d’alimentation normale : {}V", dataIncoming.tension());
        }

        // 4️⃣ Vérifier inclinaison
        if (dataIncoming.inclination() > 30) {
            log.warn("⚠️ Inclinaison anormale détectée : {}°", dataIncoming.inclination());
            pannes.add(new Panne(
                    dataIncoming.serialNumber(),
                    now,
                    "Inclinaison anormale détectée (" + dataIncoming.inclination() + "°).",
                    localisation
            ));
        } else {
            log.debug("✅ Inclinaison correcte : {}°", dataIncoming.inclination());
        }

        // 5️⃣ Cohérence lumière / voltage selon l'heure
        if (estHeureDeFonctionnement) {
            log.debug("🌙 Période nocturne détectée - la lampe devrait être allumée.");
            if (!dataIncoming.isLightOn()) {
                log.warn("⚠️ Lampe éteinte alors qu’elle devrait être allumée (nuit).");
                pannes.add(new Panne(
                        dataIncoming.serialNumber(),
                        now,
                        "Lampe éteinte alors qu’elle devrait être allumée (période nocturne).",
                        localisation
                ));
            } else if (dataIncoming.voltage() < 100) {
                log.warn("⚠️ Lampe allumée mais voltage de sortie faible : {}V", dataIncoming.voltage());
                pannes.add(new Panne(
                        dataIncoming.serialNumber(),
                        now,
                        "Lampe allumée mais voltage de sortie trop faible (" + dataIncoming.voltage() + "V).",
                        localisation
                ));
            } else {
                log.debug("✅ Éclairage actif et tension de sortie correcte : {}V", dataIncoming.voltage());
            }
        } else {
            log.debug("☀️ Période diurne - la lampe devrait être éteinte.");
            if (dataIncoming.isLightOn() && dataIncoming.voltage() > 100) {
                log.warn("⚠️ Lampe allumée en dehors des heures de fonctionnement ({}V).", dataIncoming.voltage());
                pannes.add(new Panne(
                        dataIncoming.serialNumber(),
                        now,
                        "Lampe allumée en dehors des heures de fonctionnement.",
                        localisation
                ));
            } else {
                log.debug("✅ Lampe éteinte comme prévu pendant la journée.");
            }
        }

        // 6️⃣ Présence détectée sans lumière (uniquement si période nocturne)
        if (estHeureDeFonctionnement &&
                dataIncoming.numberOfPresenceDetected() > 0 &&
                !dataIncoming.isLightOn()) {
            log.warn("⚠️ Présence détectée ({}) mais lampe éteinte.", dataIncoming.numberOfPresenceDetected());
            pannes.add(new Panne(
                    dataIncoming.serialNumber(),
                    now,
                    "Présence détectée mais lampe éteinte (nuit).",
                    localisation
            ));
        }

        // 7️⃣ Résumé
        if (pannes.isEmpty()) {
            log.info("✅ Aucune panne détectée pour le lampadaire [{}]", dataIncoming.serialNumber());
        } else {
            log.info("🚨 {} panne(s) détectée(s) pour le lampadaire [{}]", pannes.size(), dataIncoming.serialNumber());
            pannes.forEach(p -> log.info("👉 {}", p.description()));
        }

        return pannes;
    }




}
