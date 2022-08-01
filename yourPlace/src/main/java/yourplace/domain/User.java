package yourplace.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //불완전한 객체생성 막아줌
@Entity 
@Table(name = "user")//entity에 매핑되는 DB 테이블 명
@Getter
public class User implements UserDetails {
	
	// 유저 정의 클래스 (RDBMS에서 table 객체화)
	
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
	
	@OneToMany(mappedBy = "user")
	private List<Like> CafeLike = new ArrayList<Like>();


	
	// 필수 override 메소드 구현
	
	/**
	 * 사용자의 권한 목록을 collection형태로 반환
	 * -> ,로 구분되어 있는 auth 활용
	 * 단, 권한목록의 자료형은 GrantedAuthority를 구현해야 함
	 * 
	 * 권한 : ADMIN(관리자권한 = 하위 권한인 유저권한도 동시에 포함), USER(유저권한)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>(); // 권한은 중복x라서 set형태의 role 선언
		for(String role: auth.split(",")) { //auth는 admin, user 형태로 db에 저장됨
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}
	
	
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
