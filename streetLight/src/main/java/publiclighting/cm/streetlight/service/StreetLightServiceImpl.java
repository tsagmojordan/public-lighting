package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.dto.StreetLightDto;
import publiclighting.cm.streetlight.dto.StreetLightResponseDto;
import publiclighting.cm.streetlight.entity.Component;
import publiclighting.cm.streetlight.entity.Lamp;
import publiclighting.cm.streetlight.entity.StreetLight;
import publiclighting.cm.streetlight.entity.StreetLightGroup;
import publiclighting.cm.streetlight.exception.CustomException;
import publiclighting.cm.streetlight.repository.LampRepository;
import publiclighting.cm.streetlight.repository.StreetLightRepository;
import publiclighting.cm.streetlight.utils.Constant;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StreetLightServiceImpl implements StreetLightService {


    private final StreetLightRepository streetLightRepository;

    @Override
    public StreetLightResponseDto create(LampDto lampDto, StreetLightDto streetLightDto, String streetLightGroup) throws CustomException {
        return null;
    }

    @Override
    public Component findById(String componentId) {
        return null;
    }

    @Override
    public List<Component> findAllByGroup(String id) {
        return streetLightRepository.findAllByGroupId(id);
    }


}
