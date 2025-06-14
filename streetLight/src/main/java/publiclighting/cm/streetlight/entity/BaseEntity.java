package publiclighting.cm.streetlight.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    protected String id= UUID.randomUUID().toString();
    protected Date createdAt=new Date();
    protected boolean isDeleted = false;
    protected String entityName="";
    protected Date updatedAt=new Date();

}
