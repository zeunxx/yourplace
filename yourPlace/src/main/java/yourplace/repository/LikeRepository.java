package yourplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import yourplace.domain.Cafe;
import yourplace.domain.Like;
import yourplace.domain.User;

public interface LikeRepository extends JpaRepository<Like, Long>{
	
	List<Like> findAllByUser(User user);
	List<Like> findAllByCafe(Cafe cafe);
}
