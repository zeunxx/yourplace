package yourplace.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import yourplace.domain.Cafe;
import yourplace.domain.Rest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface RestRepository extends JpaRepository<Rest, Integer> {
    Page<Rest> findByRestNameContaining(String keyword, Pageable pageable);

    // 날씨 맞춤 리턴 (날씨 좋음);
    @Query(value="select * from rest_info c where rest_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Rest> selectGoodWeather() throws Exception;

    // 비
    @Query(value="select * from rest_info c where rest_name like '%전집%'  and rest_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Rest> selectRainyWeather() throws Exception;

    // 눈
    @Query(value="select * from rest_info c where rest_name like '%분식%'  and rest_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Rest> selectSnowWeather() throws Exception;

    // 더움
    @Query(value="select * from rest_info c where rest_name like '%국수%'  and rest_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Rest> selectHotWeather() throws Exception;

    // 추움
    @Query(value="select * from rest_info c where rest_name like '%라멘%'  and rest_total_rate >=3.5 order by rand() limit 3",nativeQuery=true)
    List<Rest> selectColdWeather() throws Exception;
}
