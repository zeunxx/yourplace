package yourplace.repository;

import yourplace.domain.Rest;
import yourplace.domain.RestLike;
import yourplace.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestLikeRepository extends JpaRepository<RestLike, Long> {
    Optional<RestLike> findByCodeAndRest(User user, Rest rest);
}
