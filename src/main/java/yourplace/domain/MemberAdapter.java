package yourplace.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.List;
@Getter
public class MemberAdapter extends User implements Serializable {
    private User user;

    public MemberAdapter(User user) {
        super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getAuth())));
        this.user = user;
    }
}