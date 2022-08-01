package yourplace.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

  // 요청 - 뷰 연결
  public void addViewControllers(ViewControllerRegistry registry) {	
//    registry.addViewController("/user/login").setViewName("user/login");
//    registry.addViewController("/user/admin").setViewName("user/admin");
//    registry.addViewController("/user/signup").setViewName("user/signup");
//    registry.addViewController("/user/passwdChange").setViewName("/user/passwdChange");
  }
}