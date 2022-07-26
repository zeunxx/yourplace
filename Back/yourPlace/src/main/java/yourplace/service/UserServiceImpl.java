package yourplace.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yourplace.domain.User;
import yourplace.dto.UserDto;
import yourplace.repository.RestLikesRepository;
import yourplace.repository.UserRepository;

@RequiredArgsConstructor
@Service 
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final RestLikesRepository restLikesRepository;

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
		System.out.println("저장 서비스");
		System.out.println(userDto.getEmail());

		return userRepository.save(User.builder()
				.email(userDto.getEmail())
				.nickname(userDto.getNickname())
				.auth(userDto.getAuth())
				.password(userDto.getPassword()).build()).getCode();
	}

	
	@Override
	public int emailCheck(String email) throws Exception {
		// 1 : db에 email 존재(중복)
		// 0 : 사용가능 email
		
		int result = userRepository.emailCheck(email);
		return result;
	}


	@Override
	public List<?> selectLikesList(String id) throws Exception {
		List<?> result = restLikesRepository.selectLikesList(id);
		return result;
	}

	
}
