package yourplace.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yourplace.domain.User;
import yourplace.dto.UserDto;
import yourplace.repository.UserRepository;

@RequiredArgsConstructor
@Service 
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;

	/**
	 * username이 DB에 있는지 확인(findbyemail) 없으면 예외처리
	 */
	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException{
		
		
		User user = userRepository.findByEmail(email).orElseThrow(()->
			new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : "+email));
        
		return user;
	}
	
	
	// userService에 회원정보 저장
	@Override
	public Long save(UserDto userDto) throws Exception {
		// 사용자 비밀번호를 해쉬 암호화 후 repository에 저장
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userDto.setPassword(encoder.encode(userDto.getPassword()));

		return userRepository.save(User.builder()
				.email(userDto.getEmail())
				.nickname(userDto.getNickname())
				.auth(userDto.getAuth())
				.password(userDto.getPassword()).build()).getCode();
	}

	// 회원가입시 이메일 체크
	@Override
	public int emailCheck(String email) throws Exception {
		// 1 : db에 email 존재(중복)
		// 0 : 사용가능 email
		
		int result = userRepository.emailCheck(email);
		return result;
	}

	// 비밀번호 체크
	@Override
	public int pwCheck(User user, String nowPasswd) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(nowPasswd, user.getPassword())) { //비밀번호가 일치한다면
			return 1; 
		}
		// 비밀번호 불일치
		else return 0;
	}

	// 비밀번호 변경
	@Override
	public int passwdChange(String userId, String newPasswd) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String encPasswd = encoder.encode(newPasswd);
		userRepository.passwdChange(encPasswd,userId);
		
		return 0;
	}

	
}
