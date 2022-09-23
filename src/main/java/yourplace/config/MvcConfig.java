package yourplace.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/user/login").setViewName("user/login");
//    registry.addViewController("/user/admin").setViewName("user/admin");
//    registry.addViewController("/user/signup").setViewName("user/signup");
//    registry.addViewController("/user/passwdChange").setViewName("/user/passwdChange");
//    registry.addViewController("/user/findpw").setViewName("/user/findpw");
    }
}