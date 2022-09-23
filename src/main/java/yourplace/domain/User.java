package yourplace.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;     //code가 유저 아이디

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String auth;

    @Builder
    public User(Long code, String email, String nickname, String password, String auth){
        super();
        this.code=code;
        this.email=email;
        this.nickname=nickname;
        this.password=password;
        this.auth=auth;
    }


}
