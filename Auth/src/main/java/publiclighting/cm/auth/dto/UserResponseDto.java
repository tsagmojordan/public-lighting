package publiclighting.cm.auth.dto;

import publiclighting.cm.auth.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}