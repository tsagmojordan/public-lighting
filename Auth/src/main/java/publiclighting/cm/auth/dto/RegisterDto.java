package publiclighting.cm.auth.dto;

import lombok.Data;
import publiclighting.cm.auth.enums.RoleName;

import javax.management.relation.RoleNotFoundException;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private RoleName role;
}