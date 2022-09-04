package toy1.yourplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.repository.CafeRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;

    @Transactional
    public Page<Cafe> search(String keyword, Pageable pageable) {
        return cafeRepository.findByCafeNameContaining(keyword,pageable);
    }

    public Page<Cafe>cafeList(Pageable pageable) {
        return cafeRepository.findAll(pageable);
    }

    public Cafe cafeView(Integer id) {
        return cafeRepository.findById(id).get();
    }



}
