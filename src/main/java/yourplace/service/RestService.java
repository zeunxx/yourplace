package yourplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import yourplace.domain.Rest;
import yourplace.repository.RestRepository;
import yourplace.dto.RestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RestService {
    private final RestRepository restRepository;

    @Transactional
    public Page<Rest> search(String keyword, Pageable pageable) {
        return restRepository.findByRestNameContaining(keyword,pageable);
    }

    public Page<Rest>restList(Pageable pageable) {
        return restRepository.findAll(pageable);
    }

    public Rest restView(Integer id) {
        return restRepository.findById(id).get();
    }



}
