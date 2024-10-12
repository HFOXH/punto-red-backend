package sc.pr.api.dtos.auth;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Builder
public class AuthRequest {
    private String user;
    private String password;
}
