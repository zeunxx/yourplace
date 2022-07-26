package yourplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import yourplace.domain.User;
import yourplace.dto.UserDto;

public interface UserRepository extends JpaRepository<User, Long> { 
	
	// 사용자가 Repository 인터페이스에 정해진 규칙대로 메소드를 입력하면, 
	// Spring이 알아서 해당 메소드 이름에 적합한 쿼리를 날리는 구현체를 만들어서 Bean으로 등록
	
	Optional<User> findByEmail(String email); //이메일로 회원조회

	// 쿼리로 이메일 조회
	@Query("select count(*) from User u where u.email= :email")
	int emailCheck(@Param("email")String email) throws Exception; //이메일 중복 확인

	// 유저페이지에 찜목록 출력
//	@Transactional
//	@Query(value="select ri.* "
//			+ "from User u join rest_likes rl join rest_info ri on u.code=rl.code and  rl.rest_id=ri.rest_id "
//			+ "where u.code =where u.email =:email", nativeQuery=true)
//	List<?> selectLikesList(@Param("email") String email) throws Exception;

}