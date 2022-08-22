package yourplace.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import yourplace.domain.User;
import yourplace.dto.MailDto;
import yourplace.dto.UserDto;
import lombok.RequiredArgsConstructor;
import yourplace.repository.UserRepository;

public interface UserService {


	// userService에 회원정보 저장
	public Long save(UserDto userDto)throws Exception;
	
	// 이메일 중복체크
	public int emailCheck(String email) throws Exception;
	 
	// 현재 비밀번호와 입력한 현재 비밀번호 확인
	public int pwCheck(User user, String nowPasswd) throws Exception;
	
	// 비밀번호 변경
	public int passwdChange(String userId, String newPasswd) throws Exception;
	
	// 임시 비밀번호 생성
	public MailDto createMail(String email) throws Exception;
	
	// 임시 비밀번호 메일로 전송
	public void mailSend(MailDto mailDto) throws Exception;

}
