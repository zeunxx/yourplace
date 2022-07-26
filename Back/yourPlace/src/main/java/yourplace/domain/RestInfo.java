package yourplace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //불완전한 객체생성 막아줌
@Entity 
@Table(name = "rest_info")//entity에 매핑되는 DB 테이블 명
@Getter
public class RestInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rest_id;
	private String rest_name;
	private double rest_total_rate;
	private String rest_addr;
	private String rest_hour;
	private String rest_number;
	
	@Builder
	public RestInfo(int rest_id, String rest_name, double rest_total_rate, String rest_addr, String rest_hour,
			String rest_number) {
		super();
		this.rest_id = rest_id;
		this.rest_name = rest_name;
		this.rest_total_rate = rest_total_rate;
		this.rest_addr = rest_addr;
		this.rest_hour = rest_hour;
		this.rest_number = rest_number;
	}
	
	
}
