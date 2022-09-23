package yourplace.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import yourplace.domain.User;
import yourplace.repository.UserRepository;
import yourplace.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    /**
     * username이 DB에 있는지 확인(findbyemail) 없으면 예외처리
     */
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {


        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : "+email));

        return user;
    }


    // userService에 회원정보 저장
    @Override
    public Long save(UserDto userDto) throws Exception {
        // 사용자 비밀번호를 해쉬 암호화 후 repository에 저장
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .auth(userDto.getAuth())
                .password(userDto.getPassword()).build()).getCode();
    }

    // 회원가입시 이메일 체크
    @Override
    public int emailCheck(String email) throws Exception {
        // 1 : db에 email 존재(중복)
        // 0 : 사용가능 email
        int result = userRepository.emailCheck(email);
        System.out.println(result);
        return result;
    }



    // 랜덤함수로 임시 비번 구문 생성
    private String getTempPasswd() {
        char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E',
                'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        String str="";

        int idx = 0;
        for(int i=0;i<10;i++) {
            idx=(int)(charSet.length * Math.random());
            str+=charSet[idx];
        }
        return str;

    }






}