package yourplace.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter //UserService에서 비밀번호 가져와 암호화하므로 생성자대신 setter
public class UserDto {
	//인증된 사용자 정보를 저장하는 클래스
	
	private String email;
	private String nickname;
	private String password;
	private String auth;

	
}
