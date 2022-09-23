package yourplace.service;

import yourplace.domain.User;
import yourplace.dto.UserDto;

public interface UserService {

    // userService에 회원정보 저장
    public Long save(UserDto userDto)throws Exception;

    // 이메일 중복체크
    public int emailCheck(String email) throws Exception;

    // 현재 비밀번호와 입력한 현재 비밀번호 확인

    public int pwCheck(User user, String nowPasswd) throws Exception;

    // 비밀번호 변경
    public int passwdChange(String userId, String newPasswd) throws Exception;

}
