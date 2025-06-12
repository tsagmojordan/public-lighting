package publiclighting.cm.streetlight.entity;

import jakarta.persistence.MappedSuperclass;


import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {
    protected Date createdAt=new Date();
    protected boolean isDeleted = false;
    protected String dataOf;
    protected Date updatedAt=new Date();


}
