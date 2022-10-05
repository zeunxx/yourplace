package yourplace.repository;

import org.springframework.data.jpa.repository.Query;
import yourplace.domain.Rest;
import yourplace.domain.RestLike;
import yourplace.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface RestLikeRepository extends JpaRepository<RestLike, Integer>{
    // 카페 찜


    List<RestLike> findAllByUser(User user);
    List<RestLike> findAllByRest(Rest rest);
    Optional<RestLike> findByUserAndRest(User user, Rest rest);
}
