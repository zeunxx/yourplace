package yourplace.service;


import yourplace.domain.Rest;
import yourplace.domain.RestLike;
import yourplace.domain.User;
import yourplace.dto.UserDto;
import yourplace.repository.RestLikeRepository;
import yourplace.repository.RestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class RestLikeService {

    private final RestLikeRepository restLikeRepository;
    private final RestRepository restRepository;

    public List<RestLike> selectUserLike(User user) throws Exception{
        UserDto userDto = new UserDto();
        List<RestLike> result = restLikeRepository.findAllByUser(user);
        return result;
    }


    public Optional<RestLike> findLike(User code, Rest restId) {
        return restLikeRepository.findByUserAndRest(code, restId);
    }

    //좋아요 추가 제거

    public boolean addLike(int restId, User user) {
        Rest rest = restRepository.findById(restId).orElseThrow();

        if(isNotAlreadyLike(user, rest)) {
            restLikeRepository.save(new RestLike(user, rest));
            return true;
        }
        return false;

    }
    private boolean isNotAlreadyLike(User user, Rest rest) {
        return restLikeRepository.findByUserAndRest(user,rest).isEmpty();
    }

    public void deleteLike(int restId, User user) {
        Rest rest = restRepository.findById(restId).orElseThrow();
        RestLike restLike = restLikeRepository.findByUserAndRest(user, rest).orElseThrow();
        restLikeRepository.delete(restLike);

    }
}
