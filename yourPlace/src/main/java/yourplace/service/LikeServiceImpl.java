package yourplace.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yourplace.repository.CafeRepository;
import yourplace.repository.LikeRepository;
import yourplace.repository.UserRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeServiceImpl {

	private final LikeRepository likesRepository;
    private final UserRepository usersRepository;
    private final CafeRepository cafesRepository;
}
