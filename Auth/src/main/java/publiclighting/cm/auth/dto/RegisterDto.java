package publiclighting.cm.auth.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
}