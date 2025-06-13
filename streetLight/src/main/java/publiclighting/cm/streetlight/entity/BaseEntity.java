package publiclighting.cm.streetlight.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    protected Date createdAt=new Date();
    protected boolean isDeleted = false;
    protected String entityName;
    protected Date updatedAt;


    public BaseEntity(Date createdAt, boolean isDeleted, String entityName) {
    }

    public BaseEntity() {

    }
}
