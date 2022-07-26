package yourplace.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import yourplace.dto.UserDto;
import lombok.RequiredArgsConstructor;
import yourplace.service.UserService;
import yourplace.service.UserServiceImpl;


// controller -> service -> repository?

@Controller
@RequiredArgsConstructor
public class UserRegisterController {
	
	private final UserServiceImpl userServiceImpl;
	
	
	@GetMapping(value="/signup") // 회원 가입 페이지
	public String signup_get() throws Exception{
		return "signup";
	}
	
	
	//아이디 중복체크
	@PostMapping("/signup")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email)throws Exception 
	{
		System.out.println("이메일중복");
		int result = userServiceImpl.emailCheck(email);
		System.out.println(result);
		return result;
		
	}
	
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String join(UserDto userDto) throws Exception{ // 회원 가입 처리
		
		int emailResult = userServiceImpl.emailCheck(userDto.getEmail());
		System.out.println(emailResult);
		System.out.println(userDto.getAuth());
		System.out.println(userDto.getEmail());

		try {
			if(emailResult == 1) {
				System.out.println("회원 가입 실패");
				return "signup";
			}else {
				
				System.out.println("회원 가입 성공");
				return "redirect:/login";
			}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("회원가입 에러");
			return "redirect:/signup";
		}
	}
	
	
	@RequestMapping("/mypage")
	public String myPage(HttpServletRequest request) throws Exception {
		
		return "/user/mypage";
	}
		
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	
	
	@GetMapping("/logout") //logout 
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
				.getContext().getAuthentication());
		
		return "redirect:/login"; //로그아웃시 로그인 페이지로 이동
	}
	
	

	//login 페이지에서 login post를 요청하면 스프링 시큐리티 내부에서 loadUserNameByEmail 호출해서 실행함

}
