package yourplace.service;


import yourplace.domain.Rest;
import yourplace.domain.RestLike;
import yourplace.domain.User;
import yourplace.repository.RestLikeRepository;
import yourplace.repository.RestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class RestLikeService {
    public final RestLikeRepository restLikeRepository;
    public final RestRepository restRepository;

   // public Optional<RestLike> findLike(User code, Rest restId) {
     //   return restLikeRepository.findByCodeAndRestId(code, restId);
    //}

    //좋아요 추가 제거

    public boolean addLike(User user, Integer restId) {
        //null값 넣는건지?
        Rest rest = restRepository.findById(restId).orElseThrow();
        if(isNotAlreadyLike(user, rest)) {
            restLikeRepository.save(new RestLike(user, rest));
            return true;
        }
        return false;

    }
    private boolean isNotAlreadyLike(User user, Rest rest) {
        return restLikeRepository.findByCodeAndRest(user,rest).isEmpty();
    }

}
