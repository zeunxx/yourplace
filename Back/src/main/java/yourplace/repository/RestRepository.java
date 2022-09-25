package yourplace.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yourplace.domain.Rest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface RestRepository extends JpaRepository<Rest, Integer> {
    Page<Rest> findByRestNameContaining(String keyword, Pageable pageable);
}
