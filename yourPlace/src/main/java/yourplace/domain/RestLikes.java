//package yourplace.domain;
//
//import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yourplace.dto.RestLikesIdDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity 
@Table(name = "rest_likes")//entity에 매핑되는 DB 테이블 명
@IdClass(RestLikesIdDto.class)
public class RestLikes{

	@Id
	@ManyToOne(fetch=FetchType.LAZY)   
	@JoinColumn(name="code")    
	private User code;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)   
	@JoinColumn(name="rest_id")    
	private RestInfo rest_id;
	

}
