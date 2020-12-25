package xxh.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import xxh.security.annotation.IgnoreAuth;
import xxh.security.util.SecurityUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:17 下午
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        IgnoreAuth annotation;
        if (chain instanceof HandlerMethod) {
            annotation = ((HandlerMethod) chain).getMethodAnnotation(IgnoreAuth.class);
        }
//        // 如果有@IgnoreAuth注解，则不验证token
//        if (annotation != null) {
//            return true;
//        }
        // 获取token, 并检查登录状态
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request, response);
    }
}
