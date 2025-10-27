package publiclighting.cm.streetlight.vo;

import java.time.LocalDateTime;

public record DataIncoming(String serialNumber,
                           int numberOfPresenceDetected,
                           float tension,
                           float voltage,
                           LocalDateTime dataCollectedTime,
                           boolean isLightOn,
                           float inclination) {
}
