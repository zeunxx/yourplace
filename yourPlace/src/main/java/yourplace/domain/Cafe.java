package yourplace.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Getter
@Table(name="cafe_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafe {

    @Id
    private int cafe_id;
    @Column(nullable = false)
    private String cafe_name;
    private String cafe_addr;
    private String cafe_hour;
    private String cafe_number;
    private double cafe_total_rate;



    @Builder
    public Cafe(int cafe_id, String cafe_name, String cafe_addr, String cafe_hour, String cafe_number, double cafe_total_rate) {
        this.cafe_id = cafe_id;
        this.cafe_name = cafe_name;
        this.cafe_addr = cafe_addr;
        this.cafe_hour = cafe_hour;
        this.cafe_number = cafe_number;
        this.cafe_total_rate = cafe_total_rate;
    }
    
    
	@OneToMany(mappedBy = "cafe")
	private List<Like> CafeLike = new ArrayList<Like>();
}



