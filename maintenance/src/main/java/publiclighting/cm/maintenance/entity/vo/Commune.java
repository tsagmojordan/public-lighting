package publiclighting.cm.maintenance.entity.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public record Commune( String communeId,
                      String communeName) {
}
