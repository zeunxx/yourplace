package yourplace.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import yourplace.domain.Cafe;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe,Integer>{
    Page<Cafe> findByCafeNameContaining(String keyword, Pageable pageable);
   
    
    
    // 날씨 맞춤 카페 리턴 (날씨 좋음);
    @Query(value="select * from cafe_info c where cafe_name like '%스타벅스%'  and cafe_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Cafe> selectGoodWeatherCafe() throws Exception;
    
    // 날씨 맞춤 카페 리턴 (비);
    @Query(value="select * from cafe_info c where cafe_name like '%투썸%'  and cafe_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Cafe> selectRainyWeatherCafe() throws Exception;
    
    // 날씨 맞춤 카페 리턴 (눈);
    @Query(value="select * from cafe_info c where cafe_name like '%할리스%'  and cafe_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Cafe> selectSnowWeatherCafe() throws Exception;
    
    // 날씨 맞춤 카페 리턴 (더움);
    @Query(value="select * from cafe_info c where cafe_name like '%설빙%'  and cafe_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Cafe> selectHotWeatherCafe() throws Exception;
    
    // 날씨 맞춤 카페 리턴 (추움);
    @Query(value="select * from cafe_info c where cafe_name like '%설빙%'  and cafe_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Cafe> selectColdWeatherCafe() throws Exception;
}
