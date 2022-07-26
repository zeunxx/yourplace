package yourplace.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import yourplace.domain.User;
import yourplace.dto.UserDto;
import lombok.RequiredArgsConstructor;
import yourplace.repository.UserRepository;

public interface UserService {


	// userService에 회원정보 저장
	public Long save(UserDto userDto)throws Exception;
	
	// 이메일 중복체크
	public int emailCheck(String email) throws Exception;
	
	// 찜목록 출력
	 public List<?> selectLikesList(String id) throws Exception;
}
