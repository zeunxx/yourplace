package yourplace.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import yourplace.dto.UserDto;
import yourplace.service.UserServiceImpl;


public class UserLikesController {

	public UserServiceImpl userServiceImpl;

//	@GetMapping("/user")
//	public String mypage(HttpSession session, Model model) throws Exception { // 마이페이지 load 및 찜목록 띄우기
//		List<?> result =userServiceImpl.selectLikesList(session.getId());
//		System.out.println(result);
//		return "user";
//	}
	
//	@GetMapping("/user")
//	public String user() {
//		return "user";
//	}
}
