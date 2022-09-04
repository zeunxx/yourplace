package toy1.yourplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.domain.Like;
import toy1.yourplace.domain.User;
import toy1.yourplace.dto.LikeDto;
import toy1.yourplace.repository.CafeRepository;
import toy1.yourplace.repository.LikeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    public final LikeRepository likeRepository;
    public final CafeRepository cafeRepository;

    public Optional<Like> findLike(User code, Cafe cafeId) {
        return likeRepository.findByCodeAndCafeId(code, cafeId);
    }

    //좋아요 추가 제거

    public boolean addLike(int cafeId, User user) {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow();
        if(isNotAlreadyLike(user, cafe)) {
            likeRepository.save(new Like(user, cafe));
            return true;
        }
        return false;

    }
    private boolean isNotAlreadyLike(User user, Cafe cafe) {
        return likeRepository.findByCodeAndCafeId(user,cafe).isEmpty();
    }

}
