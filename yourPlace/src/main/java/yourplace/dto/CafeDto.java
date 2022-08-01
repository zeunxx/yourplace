package yourplace.dto;

import lombok.Builder;
import lombok.Data;
import yourplace.domain.Cafe;

import javax.persistence.Column;

@Data
public class CafeDto {
    private int cafe_id;
    private String cafe_name;
    private String cafe_addr;
    private String cafe_hour;
    private String cafe_number;
    private double cafe_total_rate;

    public Cafe toEntity() {
        return Cafe.builder()
                .cafe_id(cafe_id)
                .cafe_name(cafe_name)
                .cafe_addr(cafe_addr)
                .cafe_hour(cafe_hour)
                .cafe_number(cafe_number)
                .cafe_total_rate(cafe_total_rate)
                .build();
    }

    @Builder
    public CafeDto(int cafe_id, String cafe_name, String cafe_addr, String cafe_hour, String cafe_number, double cafe_total_rate) {
        this.cafe_id = cafe_id;
        this.cafe_name = cafe_name;
        this.cafe_addr = cafe_addr;
        this.cafe_hour = cafe_hour;
        this.cafe_number = cafe_number;
        this.cafe_total_rate = cafe_total_rate;
    }
}
