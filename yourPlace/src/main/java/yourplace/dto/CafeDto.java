package yourplace.dto;

import lombok.Builder;
import lombok.Data;
import yourplace.domain.Cafe;

import javax.persistence.Column;

@Data
public class CafeDto {
    private int cafeId;
    private String cafeName;
    private String cafeAddr;
    private String cafeHour;
    private String cafeNumber;
    private double cafeTotalRate;


}
