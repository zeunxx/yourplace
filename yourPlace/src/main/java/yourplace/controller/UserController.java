package yourplace.controller;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import yourplace.domain.User;
import yourplace.dto.UserDto;
import lombok.RequiredArgsConstructor;
import yourplace.service.UserService;
import yourplace.service.UserServiceImpl;


// controller -> service -> repository?

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	
	// 회원 가입 페이지
	@GetMapping(value="/user/signup") 
	public String signup_get() throws Exception{
		System.out.println("유저 회원가입 페이지 들어옴");
		return "/user/signup";
	}
	
	
	//아이디 중복체크
	@PostMapping("/user/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email)throws Exception 
	{
		System.out.println("이메일 중복 체크");
		int result = userServiceImpl.emailCheck(email);
		return result;
		
	}
	
	// 회원가입 처리
	@RequestMapping(value="/user/signup", method=RequestMethod.POST)
	public String join(UserDto userDto) throws Exception{ 
		
		int emailResult = userServiceImpl.emailCheck(userDto.getEmail());
		System.out.println(emailResult);
		System.out.println(userDto.getAuth());
		System.out.println(userDto.getEmail());

		try {
			if(emailResult == 1) {
				System.out.println("회원 가입 실패");
				return "user/signup";
			}else {
				userServiceImpl.save(userDto);
				System.out.println("회원 가입 성공");
				return "/user/login";
			}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("회원가입 에러");
			return "/user/signup";
		}
	}
	
	// 마이페이지 로드
	@RequestMapping("/user/mypage")
	public String myPage() throws Exception {
		return "/user/mypage";
	}
		
	// 로그인 페이지 로드
	@GetMapping("/user/login")
	public String login(@AuthenticationPrincipal User user) {
		if(user==null) {
			return "/user/login";
		}
		
		return "redirect:/user/mypage";
	}
	
	// 비밀번호 변경 페이지 로드
	@GetMapping("/user/passwdChange")
	public String passwdChange_page() {
		
		return "/user/passwdChange";
	}
	
	
	// 비밀번호 변경 로직
	@PostMapping("/user/passwdChange")
	public String passwdChange(Principal principal,String newPasswd) throws Exception {

		//로그인된 객체 얻어오기
		String userId = principal.getName();
		// 비밀번호 변경
		if((userServiceImpl.passwdChange(userId,newPasswd))==0) {
			return "redirect:/user/mypage";
		}
		else {
			return "redirect:/index";
		}
		
	}
	
	//유저 비밀번호와 현재 비번 입력값 일치 로직
	@PostMapping("/user/passwdCheck")  
	@ResponseBody
	public int passwdCheck(@RequestParam("passwd") String nowPasswd) throws Exception {
		
		//로그인된 객체 얻어오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		User user = (User)principal;
		int result = userServiceImpl.pwCheck(user, nowPasswd);
		// result =1 이면 현재 비밀번호와 일치 =0이면 불일치
		return result;
	}
	
	
	
	@GetMapping("/logout") //logout 
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
				.getContext().getAuthentication());
		
		return "redirect:/user/login"; //로그아웃시 로그인 페이지로 이동
	}
	
	

	//login 페이지에서 login post를 요청하면 스프링 시큐리티 내부에서 loadUserNameByEmail 호출해서 실행함

}