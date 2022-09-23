package yourplace.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import yourplace.domain.User;
import yourplace.dto.UserDto;
import yourplace.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

    @Controller
    @RequestMapping("/user")
    @RequiredArgsConstructor
    public class UserController {

        private final UserServiceImpl userServiceImpl;

        // 회원 가입 페이지

        @GetMapping(value = "/signup")
        public String signup_get(Model model) throws Exception {
            model.addAttribute("userDto", new UserDto());
            return "/user/signup";
        }


        //아이디 중복체크
        @PostMapping("/emailCheck")
        @ResponseBody
        public int emailCheck(@RequestParam("email") String email) throws Exception {
            int result = userServiceImpl.emailCheck(email);
            return result;

        }

        // 회원가입 처리
        @RequestMapping(value = "/join", method = RequestMethod.POST)
        public String join(@Valid UserDto userDto, BindingResult bindingResult, Model model) throws Exception {

            if (bindingResult.hasErrors()) {
                return "/user/signup";
            }

            int emailResult = userServiceImpl.emailCheck(userDto.getEmail());

            try {
                if (emailResult == 1) {
                    System.out.println("회원 가입 실패");
                    return "/user/signup";
                } else {
                    userServiceImpl.save(userDto);
                    System.out.println("회원 가입 성공");
                    return "/user/login";
                }
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getMessage());
                return "/user/signup";
            }
        }


        // 로그인 페이지 로드
        @GetMapping("/login")
        public String login(@AuthenticationPrincipal User user) {
            if (user == null) {
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
        public String passwdChange(Principal principal, String newPasswd) throws Exception {

            //로그인된 객체 얻어오기
            String userId = principal.getName();
            // 비밀번호 변경
            if ((userServiceImpl.passwdChange(userId, newPasswd)) == 0) {
                return "redirect:/user/mypage";
            } else {
                return "redirect:/index";
            }

        }



    }

