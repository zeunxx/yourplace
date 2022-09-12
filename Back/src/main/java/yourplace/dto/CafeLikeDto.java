package yourplace.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yourplace.domain.Cafe;
import yourplace.domain.CafeLike;

import yourplace.domain.User;


@NoArgsConstructor
@Setter
@Getter
@Data
public class CafeLikeDto {
	private Long id;
	private int cafe_id;
	private Long code;
	
	@Builder
	public CafeLikeDto(CafeLike cafeLike) {
		super();
		this.id = cafeLike.getId();
		this.cafe_id = cafeLike.getCafe().getCafeId();
		this.code = cafeLike.getUser().getCode();
	} 
	
	

}
