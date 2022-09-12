package yourplace.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import yourplace.domain.Cafe;
import yourplace.domain.CafeLike;
import yourplace.domain.User;
import yourplace.dto.CafeLikeDto;
import yourplace.dto.MailDto;
import yourplace.dto.UserDto;
import lombok.RequiredArgsConstructor;
import yourplace.service.CafeLikeService;
import yourplace.service.CafeService;
import yourplace.service.UserService;
import yourplace.service.UserServiceImpl;


// controller -> service -> repository?

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	private final CafeLikeService cafeLikeService;
	private final CafeService cafeService;
	
	// 회원 가입 페이지
	
	@GetMapping(value="/signup") 
	public String signup_get(Model model) throws Exception{
		model.addAttribute("userDto",new UserDto());
		return "/user/signup";
	}

	
	//아이디 중복체크
	@PostMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email)throws Exception 
	{
		System.out.println("중복체크");
		int result = userServiceImpl.emailCheck(email);
		return result;
		
	}
	
	// 회원가입 처리
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@Valid UserDto userDto,BindingResult bindingResult, Model model) throws Exception{ 
		
		if(bindingResult.hasErrors()) {
			return "/user/signup";
		}
		
		int emailResult = userServiceImpl.emailCheck(userDto.getEmail());
		
		try {
			if(emailResult == 1) {
				System.out.println("회원 가입 실패");
				return "/user/signup";
			}else {
				userServiceImpl.save(userDto);
				System.out.println("회원 가입 성공");
				return "/user/login";
			}
		}catch(Exception e) {
			model.addAttribute("errorMessage",e.getMessage());
			return "/user/signup";
		}
	}
	
	
	// 로그인 페이지 로드
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if(user==null) {
			return "/user/login";
		}
		
		return "/user/mypage";
	}
	
	// 비밀번호 변경 페이지 로드
	@GetMapping("/passwdChange")
	public String passwdChange_page() {
		
		return "/user/passwdChange";
	}
	
	
	// 비밀번호 변경 로직
	@PostMapping("/passwdChange")
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
	@PostMapping("/passwdCheck")  
	@ResponseBody
	public int passwdCheck(@RequestParam("passwd") String nowPasswd) throws Exception {
		
		//로그인된 객체 얻어오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		User user = (User)principal;
		int result = userServiceImpl.pwCheck(user, nowPasswd);
		// result =1 이면 현재 비밀번호와 일치 =0이면 불일치
		return result;
	}
	
	
	
	// 비밀번호 찾기 페이지 로드 및 임시 비번 메일로 전송
	@GetMapping("/find_pw")
	public String findpw() throws Exception {

		return "/user/find_pw";
	}
	
	@PostMapping("/find_pw_form")
	@Transactional
	public String findPwForm(@RequestParam("email") String email, Model model ) throws Exception{
	
		int emailResult = userServiceImpl.emailCheck(email);
	
		boolean result = false;
		if(emailResult == 1) {
			result = true;
			model.addAttribute("result",result);
			MailDto mailDto = userServiceImpl.createMail(email);
			userServiceImpl.mailSend(mailDto);
			
		}else {
			model.addAttribute("result",result);
		}
		
		return "/user/find_pw";
	}
	
	
	@GetMapping("/mypage") //마이페이지에 찜목록 출력
	public String testLike(Model model) throws Exception {
		
		//현재 로그인한 객체 정보 얻어옴
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		User user = (User)principal; 
		
		//현재 요저의 찜목록 select 로직 
		List<CafeLike> likeList = cafeLikeService.selectUserLike(user);
		// Like를 LikeDto로 바꾼후 list로 반환
		List<CafeLikeDto> cafeLikeDto = likeList.stream().map(CafeLikeDto::new).collect(Collectors.toList());
		
		
		
		// 찜목록의 카페id로 카페 findById 실행해 카페정보 불러옴
		String [][] cafe= new String[cafeLikeDto.size()][2];
		int i=0;
		for(CafeLikeDto like : cafeLikeDto) {
			Cafe cafeInfo =cafeService.cafeView(like.getCafe_id());
		
			String cafeName = cafeInfo.getCafeName();
			String cafeAddr = cafeInfo.getCafeAddr();
			cafe[i][0]=cafeName;
			cafe[i][1]=cafeAddr;
			
			i++;
		}

		model.addAttribute("cafeInfo",cafe);
	
		return "user/mypage";
	}

	

	//login 페이지에서 login post를 요청하면 스프링 시큐리티 내부에서 loadUserNameByEmail 호출해서 실행함

}