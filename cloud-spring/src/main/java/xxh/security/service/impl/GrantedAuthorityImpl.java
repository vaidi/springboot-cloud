package xxh.security.service.impl;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:32 下午
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
