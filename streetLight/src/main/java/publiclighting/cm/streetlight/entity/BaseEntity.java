package publiclighting.cm.streetlight.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
    protected Date createdAt=new Date();
    protected boolean isDeleted = false;
    protected String entityName;
    protected Date updatedAt=new Date();

}
