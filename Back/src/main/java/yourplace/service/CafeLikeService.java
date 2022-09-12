package yourplace.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yourplace.domain.Cafe;
import yourplace.domain.CafeLike;
import yourplace.domain.CafeLike;
import yourplace.domain.User;
import yourplace.dto.UserDto;
import yourplace.repository.CafeLikeRepository;
import yourplace.repository.CafeRepository;
import yourplace.repository.UserRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class CafeLikeService {

	private final CafeLikeRepository cafeLikeRepository;
    private final CafeRepository cafeRepository;
    
    public List<CafeLike> selectUserLike(User user) throws Exception{
    	UserDto userDto = new UserDto();
    	List<CafeLike> result = cafeLikeRepository.findAllByUser(user);
    	
    	return result;
    }
    

    /**
     * 
     * 카페 
     *
    public Optional<CafeLike> findLike(User code, Cafe cafeId) {
        return likeRepository.findByCodeAndCafeId(code, cafeId);
    }

    //좋아요 추가 제거

    public boolean addLike(int cafeId, User user) {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow();
        if(isNotAlreadyLike(user, cafe)) {
            likeRepository.save(new CafeLike(user, cafe));
            return true;
        }
        return false;

    }
    private boolean isNotAlreadyLike(User user, Cafe cafe) {
        return likeRepository.findByCodeAndCafeId(user,cafe).isEmpty();
    }**/
}
