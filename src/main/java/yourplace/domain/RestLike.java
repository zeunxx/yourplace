package yourplace.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor(access = AccessLevel.PROTECTED) //불완전한 객체생성 막아줌
@Entity
@Table(name = "rest_likes")//entity에 매핑되는 DB 테이블 명
@Getter
public class RestLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    @ManyToOne
    @JoinColumn(name = "code")
    private User user;

    @ManyToOne
    @JoinColumn(name = "rest_id")
    private Rest rest;

    @Builder
    public RestLike(User user, Rest rest){
        this.user = user;
        this.rest = rest;
    }

}