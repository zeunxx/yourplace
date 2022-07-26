package yourplace.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import yourplace.domain.RestLikes;
import yourplace.domain.User;
import yourplace.dto.RestLikesIdDto;

public interface RestLikesRepository extends JpaRepository<RestLikes, RestLikesIdDto>{

	
	// 유저페이지에 찜목록 출력
	@Transactional
	@Query(value="select ri.* "
			+ "from User u join RestLikes rl join RestInfo ri on u.code=rl.code and  rl.rest_id=ri.rest_id "
			+ "where u.code =where u.email =:email",nativeQuery=true)
	List<?> selectLikesList(@Param("email") String email) throws Exception;

}
