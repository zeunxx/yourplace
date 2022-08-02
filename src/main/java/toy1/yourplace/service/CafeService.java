package toy1.yourplace.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.repository.CafeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


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

}
