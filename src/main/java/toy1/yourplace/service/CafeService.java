package toy1.yourplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.repository.CafeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository=cafeRepository;
    }

    @Transactional
    public Page<Cafe> search(String keyword, Pageable pageable) {
        return cafeRepository.findByTitleContaining(keyword, pageable);
    }






}
