package yourplace.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter 
@Setter //UserService에서 비밀번호 가져와 암호화하므로 생성자대신 setter
public class UserDto {
	//인증된 사용자 정보를 저장하는 클래스
	
	@NotBlank(message="이메일은 필수 입력 값입니다.")
	@Email(message="이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotBlank(message="이름은 필수 입력 값입니다.")
	private String nickname;
	
	@NotBlank(message="비밀번호는 필수 입력 값입니다.")
	@Length(min=4, max=16, message="비밀번호는 4자이상, 16자 이하로 입력해주세요.")
	private String password;
	private String auth;
	
	
	@Builder
	public UserDto(String email, String nickname, String password, String auth) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.auth = auth;
	}

	
}
