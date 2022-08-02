package toy1.yourplace.dto;

import lombok.Builder;
import lombok.Data;
import toy1.yourplace.domain.Cafe;

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
