package yourplace.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import yourplace.domain.Cafe;
import yourplace.domain.Like;
import yourplace.domain.User;
import yourplace.dto.CafeDto;
import yourplace.dto.LikeDto;
import yourplace.service.CafeService;
import yourplace.service.LikeServiceImpl;

@Controller
@RequiredArgsConstructor
public class CafeController {
	
	private final LikeServiceImpl likeServiceImpl;
	private final CafeService cafeService;

	 
	@GetMapping("/user/mypage")
	public String testLike(Model model) throws Exception {
		
		//현재 로그인한 객체 정보 얻어옴
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		User user = (User)principal; 
		
		//현재 요저의 찜목록 select 로직 
		List<Like> likeList = likeServiceImpl.selectUserLike(user);
		// Like를 LikeDto로 바꾼후 list로 반환
		List<LikeDto> likeDto = likeList.stream().map(LikeDto::new).collect(Collectors.toList());
		
		
		
		// 찜목록의 카페id로 카페 findById 실행해 카페정보 불러옴
		String [][] cafe= new String[likeDto.size()][2];
		int i=0;
		for(LikeDto like : likeDto) {
			Cafe cafeInfo =cafeService.cafeView(like.getCafe_id());
		
			String cafeName = cafeInfo.getCafeName();
			String cafeAddr = cafeInfo.getCafeAddr();
			cafe[i][0]=cafeName;
			cafe[i][1]=cafeAddr;
			
			i++;
		}

		model.addAttribute("likeList",likeDto);
		model.addAttribute("cafeInfo",cafe);
		//ResponseEntity<List<LikeDto>>
		//return new ResponseEntity<>( likeDto ,HttpStatus.OK);
		return "user/mypage";
	}
	


}
