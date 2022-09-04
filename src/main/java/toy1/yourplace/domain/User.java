package toy1.yourplace.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code; //PK

    @Column(unique = true, length=50, nullable = false)
    private String email; // 이메일이자 id (따라서 null값 x && unique)

    @Column (nullable = false)
    private String nickname; //사용자 닉네임

    @Column(nullable=false)
    private String password; //비밀번호

    @Column(nullable=false)
    private String auth; // 권한

    @Builder //빌더 패턴
    public User(String email, String nickname ,String password, String auth) {
        super();
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.auth = auth;
    }

    public <E> User(String email, String password, List<E> es) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>(); // 권한은 중복x라서 set형태의 role 선언
        for(String role: auth.split(",")) { //auth는 admin, user 형태로 db에 저장됨
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }
    @OneToMany(mappedBy = "user")
    private List<Like> cafeLike = new ArrayList<Like>();


    @Override
    public String getPassword() {
        return password;
    }

    // 사용자의 unique한 값 return(pk 또는 id)
    @Override
    public String getUsername() {
        return email;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true; //만료되지 않음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true; //잠기지 않음
    }

    // 비밀번호 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정의 활성화 여부 반환
    @Override
    public boolean isEnabled() {
        return true; // 활성화 ㅇ
    }

}
