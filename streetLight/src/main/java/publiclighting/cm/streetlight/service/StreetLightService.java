package publiclighting.cm.streetlight.service;


import jakarta.transaction.Transactional;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.StreetLight;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.vo.DataIncoming;
import publiclighting.cm.streetlight.vo.Panne;

import java.util.List;


public interface StreetLightService {

    StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, Long streetLightGroup) throws CustomException;

    Component findById(String componentId);

    List<Component> findAllByGroup(Long id);
    StreetLight findById(Long id) throws CustomException;
    List<StreetLight> findAllByParentId(Long id) throws CustomException;


    List<Panne> verifierPanne(DataIncoming dataIncoming);
}
