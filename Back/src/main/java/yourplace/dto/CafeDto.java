package yourplace.dto;

import lombok.Builder;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yourplace.domain.Cafe;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
public class CafeDto {
    private int cafeId;
    private String cafeName;
    private String cafeAddr;
    private String cafeHour;
    private String cafeNumber;
    private double cafeTotalRate;
    
    @Builder
	public CafeDto(Cafe cafe) {
		super();
		this.cafeId = cafe.getCafeId();
		this.cafeName = cafe.getCafeName();
		this.cafeAddr = cafe.getCafeAddr();
		this.cafeHour = cafe.getCafeHour();
		this.cafeNumber = cafe.getCafeNumber();
		this.cafeTotalRate = cafe.getCafeTotalRate();
	}

    

}
