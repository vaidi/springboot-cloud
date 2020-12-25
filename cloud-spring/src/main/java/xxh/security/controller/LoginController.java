package xxh.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxh.security.bean.HttpResult;
import xxh.security.bean.User;
import xxh.security.common.JwtAuthenticatioToken;
import xxh.security.util.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 2:47 下午
 */
@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody User loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
        return HttpResult.ok(token);
    }

}
