package publiclighting.cm.streetlight.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {
    protected LocalDateTime createdAt;
    protected boolean isDeleted;
    private String entityType;
    protected LocalDateTime updatedAt;


}
