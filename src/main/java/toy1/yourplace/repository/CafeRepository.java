package toy1.yourplace.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy1.yourplace.domain.Cafe;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe,Integer>{
    Page<Cafe> findByCafeNameContaining(String keyword, Pageable pageable);

}
