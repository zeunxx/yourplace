package yourplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import yourplace.domain.Cafe;
import yourplace.domain.CafeLike;
import yourplace.domain.User;

public interface CafeLikeRepository extends JpaRepository<CafeLike, Integer>{
	// 카페 찜
	List<CafeLike> findAllByUser(User user);
	List<CafeLike> findAllByCafe(Cafe cafe);
	Optional<CafeLike> findByUserAndCafe(User user, Cafe cafe);
}
