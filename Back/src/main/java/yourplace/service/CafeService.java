package yourplace.service;



import lombok.RequiredArgsConstructor;
import yourplace.domain.Cafe;
import yourplace.domain.User;
import yourplace.repository.CafeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeService {
    private final CafeRepository cafeRepository;


    
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
