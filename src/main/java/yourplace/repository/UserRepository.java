package yourplace.repository;

import yourplace.domain.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 사용자가 Repository 인터페이스에 정해진 규칙대로 메소드를 입력하면,
    // Spring이 알아서 해당 메소드 이름에 적합한 쿼리를 날리는 구현체를 만들어서 Bean으로 등록
    @NonNull
    Optional<User> findByEmail(String email); //이메일로 회원조회

    // 쿼리로 이메일 조회
    @Query("select count(*) from User u where u.email= :email")
    int emailCheck(@Param("email")String email) throws Exception; //이메일 중복 확인

    // 비밀번호 update
    @Modifying
    @Transactional
    @Query("update User u set u.password=:passwd where u.email=:userId")
    int passwdChange(@Param("passwd") String passwd,@Param("userId") String userId) throws Exception;
}
