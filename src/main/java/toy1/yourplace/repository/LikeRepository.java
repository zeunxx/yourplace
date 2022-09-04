package toy1.yourplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.domain.Like;
import toy1.yourplace.domain.User;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByCodeAndCafeId(User code, Cafe cafeId);

}
