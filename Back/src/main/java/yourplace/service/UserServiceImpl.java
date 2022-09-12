package yourplace.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import yourplace.dto.MailDto;
import yourplace.dto.UserDto;
import yourplace.repository.UserRepository;


@Service 
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final MailSender sender;
	
	/**
	 * username이 DB에 있는지 확인(findbyemail) 없으면 예외처리
	 */
	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException{
		
		
		User user = userRepository.findByEmail(email).orElseThrow(()->
			new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : "+email));
        
		return user;
	}
	
	
	// 회원정보 저장
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
		System.out.println(result);
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

	
	// 임시 비밀번호 생성
	@Override
	public MailDto createMail(String email) throws Exception {
		
		// 임시 비번 random하게 생성
		String tempPass = getTempPasswd();
		
		// 임시 비번으로 db 업데이트 !!!!!!!!!!!!!!!!!!!!!! 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwd = encoder.encode(tempPass);
		int result = userRepository.passwdChange(passwd, email);
		
		// MailDto
		MailDto mailDto = new MailDto();
		mailDto.setAddress(email);
		mailDto.setMessage("안녕하세요 YourPlace 임시 비밀번호 안내 이메일입니다."+
				"회원님의 임시 비밀번호는 "+tempPass+" 입니다. 로그인 후 비밀번호를 변경해주세요.");
		mailDto.setTitle("YourPlace 임시 비밀번호 안내 이메일입니다.");
		return mailDto;
	}

	// 랜덤함수로 임시 비번 구문 생성
	private String getTempPasswd() {
		char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E',
				'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		String str="";
		
		int idx = 0;
		for(int i=0;i<10;i++) {
			idx=(int)(charSet.length * Math.random());
			str+=charSet[idx];
		}
		return str;

	}

	//임시 비번 메일 전송
	@Override
	public void mailSend(MailDto mailDto) throws Exception {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDto.getAddress());
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		message.setFrom("misely200@naver.com");
		message.setReplyTo("misely200@naver.com");
		sender.send(message);
		
		System.out.println(message);
		System.out.println("전송 완료");
		
		
	}

	
	
}
