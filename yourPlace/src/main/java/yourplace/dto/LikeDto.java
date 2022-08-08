package yourplace.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yourplace.domain.Cafe;
import yourplace.domain.Like;
import yourplace.domain.User;


@NoArgsConstructor
@Setter
@Getter
@Data
public class LikeDto {
	private Long id;
	private int cafe_id;
	private Long code;
	
	@Builder
	public LikeDto(Like like) {
		super();
		this.id = like.getId();
		this.cafe_id = like.getCafe().getCafeId();
		this.code = like.getUser().getCode();
	} 
	
	

}
