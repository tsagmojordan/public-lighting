package publiclighting.cm.streetlight.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Date createdAt=new Date();
    protected boolean isDeleted = false;
    protected String entityName="";
    protected Date updatedAt=new Date();

    public BaseEntity(Date createAt, boolean isDeleted, String entityName, Date updateAt) {
    }
}
