package xxh.security.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:05 下午
 */
public class JwtUserDetails  extends User{
    private static final long serialVersionUID = 1L;

    public JwtUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    public JwtUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                          boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


}
