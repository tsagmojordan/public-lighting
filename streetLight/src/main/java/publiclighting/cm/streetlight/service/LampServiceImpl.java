package publiclighting.cm.streetlight.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import publiclighting.cm.streetlight.dto.LampDto;
import publiclighting.cm.streetlight.entity.Lamp;
import publiclighting.cm.streetlight.repository.LampRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LampServiceImpl implements LampService {

    private final LampRepository lampRepository;

    @Override @Transactional
    public Lamp createLamp(LampDto lampDto) {
        return lampRepository.save(Lamp.builder()
                .price(lampDto.getPrice())
                .lampType(lampDto.getLampType())
                .power(lampDto.getPower())
                .lifeDuration(lampDto.getLifeDuration())
                .build());
    }
}
