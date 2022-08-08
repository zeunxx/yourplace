package yourplace.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yourplace.domain.Like;
import yourplace.domain.User;
import yourplace.dto.UserDto;
import yourplace.repository.CafeRepository;
import yourplace.repository.LikeRepository;
import yourplace.repository.UserRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeServiceImpl {

	private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    
    public List<Like> selectUserLike(User user) throws Exception{
    	UserDto userDto = new UserDto();
    	List<Like> result = likeRepository.findAllByUser(user);
    	
    	return result;
    }
}
