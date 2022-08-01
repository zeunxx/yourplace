package yourplace.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yourplace.domain.Cafe;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe,Integer>{
    // Page<Cafe> findByTitleContaining(String keyword, Pageable pageable);


}