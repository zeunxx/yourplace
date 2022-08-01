package yourplace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //불완전한 객체생성 막아줌
@Entity 
@Table(name = "cafe_likes")//entity에 매핑되는 DB 테이블 명
@Getter
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //PK
	
	@ManyToOne
    @JoinColumn(name = "code")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Builder
    public Like(User user, Cafe cafe){
        this.user = user;
        this.cafe = cafe;
    }

}
