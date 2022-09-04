package toy1.yourplace.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {

    private Long code;
    private String email;
    private String nickname;
    private String password;
    private String auth;


}