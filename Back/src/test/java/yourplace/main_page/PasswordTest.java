package yourplace.main_page;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import yourplace.domain.User;
import yourplace.dto.CafeDto;
import yourplace.dto.UserDto;
import yourplace.service.UserServiceImpl;

public class PasswordTest {

	UserServiceImpl userServiceImpl;
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeEach
	public void setup() throws Exception {
		UserDto userDto= new UserDto("testUser@naver.com", "test" ,"1234", "USER");
		userServiceImpl.save(userDto);
		mvc = MockMvcBuilders
				.webAppContextSetup(this.context)
				.apply(springSecurity())
				.build();
	}
	
	
	@Test
	@DisplayName("비밀번호 변경 후 로그인 테스트")
	void passwordChange() throws Exception {
		// given : 유저 id와 new pw 파라미터로 넘김
		int result = userServiceImpl.passwdChange("testUser@naver.com", "5678");
		String userId ="testUser@naver.com";
		String newPw="5678";
		
		// when : 새로운 비밀번호로 로그인
		mvc.perform(formLogin().user(userId).password(newPw))
		.andDo(print())
		
		// then : 로그인 됨
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));
	}
	
}
