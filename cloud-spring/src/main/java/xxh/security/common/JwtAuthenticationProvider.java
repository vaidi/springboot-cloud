package xxh.security.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:01 下午
 */
@Slf4j
public class JwtAuthenticationProvider  extends DaoAuthenticationProvider {
    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate authentication:{}",authentication);
        //todo 可以在此处覆写整个登录认证逻辑
        return super.authenticate(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        log.info("additionalAuthenticationChecks userDetails:{}",userDetails);
        //todo 可以在此处覆写密码验证逻辑
        super.additionalAuthenticationChecks(userDetails, authentication);
    }

}
