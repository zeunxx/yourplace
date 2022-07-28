package yourplace.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import lombok.RequiredArgsConstructor;
import yourplace.service.UserService;
import yourplace.service.UserServiceImpl;


@Configuration // bean 관리
@RequiredArgsConstructor // lombok 필수값(final 등)으로 생성자 생성
@EnableWebSecurity // spring sequrity 사용 위한 어노테이션
public class UserConfig extends WebSecurityConfigurerAdapter{
	// 유저 정보 클래스 config 파일
	
	private final UserServiceImpl userServiceImpl; // 유저 정보 가져올 클래스
	
	@Override
	// 인증 무시할 경로(css,js,img,h2-console은 인증하지 않아도 접근 가능해야함)
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**","/js/**","/img/**","h2-console/**","/login");
	}

	
	@Override
	// http 관련 인증 설정 기능
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http
			//.httpBasic().disable() 
			// url별 관리 설정
			.authorizeRequests() // andMatchers() 사용하면 url/http 메소드 별로 관리 가능
					.antMatchers("/user/login","/user/signup","/").permitAll() // 로그인,회원가입,메인 페이지 아무나 접근 가능
					.antMatchers("/user/mypage","/user/passwdChange").hasRole("USER") // USER(유저), ADMIN(관리자)만 접근가능
					.antMatchers("/user/admin").hasRole("ADMIN") // ADMIN만 접근가능
					.anyRequest().authenticated() // 나머지는 권한 있으면 접근 가능
					
			.and()
				.formLogin() // 로그인 설정
					.loginPage("/user/login") //로그인 페이지 링크
					.defaultSuccessUrl("/") // 로그인 성공시 연결되는 주소
					
			.and()
				.logout() //로그아웃 설정
					.logoutSuccessUrl("/") //로그아웃시 연결되는 주소(메인페이지로 이동)
					.invalidateHttpSession(true) //로그아웃시 저장해 둔 세션 날리기
					
		;
	}
	
	@Override	
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		// 로그인시 필요한 정보 가져옴
		
		auth.userDetailsService(userServiceImpl) //유저 정보는 userService에서 가져옴
			.passwordEncoder(new BCryptPasswordEncoder()); //패스워드 인코더 설정
							// (+ 제출된 비밀번호와 DB에 있는 비밀번호 일치 여부 알려주는 메소드 제공)
	}
	

	
	
}
