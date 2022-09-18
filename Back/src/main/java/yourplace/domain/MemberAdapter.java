package yourplace.domain;



import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;

@Getter
public class MemberAdapter extends User implements Serializable {
    private yourplace.domain.User user;

    public MemberAdapter(yourplace.domain.User user) {
        super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority((user.getAuth()))));
    }

}