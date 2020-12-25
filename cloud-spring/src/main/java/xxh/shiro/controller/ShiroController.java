package xxh.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2020/11/25 4:29 下午
 */
@Slf4j
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @GetMapping("/a")
    @ResponseBody
    public String aa() throws InterruptedException {
        boolean circleOk = true;
        while (circleOk){

            System.out.println("我爱你!!!");
            sleep(2000);
        }
        return UUID.randomUUID().toString();
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setTimeout(100000L);
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        SecurityManager securityManager = SecurityUtils.getSecurityManager();


        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @RequiresPermissions("user:list")
    @ResponseBody
    @GetMapping("/show")
    public String showUser(HttpServletRequest httpRequest) {
        log.info("httpRequest:{}", httpRequest);
        Subject subject = SecurityUtils.getSubject();
        Object obj = subject.getPrincipal();
        Session session = subject.getSession();
        session.setAttribute("someKey", "aValue");
        log.info("subject:{},obj:{}", subject, obj);
        return "这是学生信息";
    }


    @RequiresPermissions("user:name")
    @ResponseBody
    @GetMapping("/showName")
    public String showName() {
        Subject subject = SecurityUtils.getSubject();
        Object obj = subject.getPrincipal();
        Session session = subject.getSession();
        Object objectValue = session.getAttribute("someKey");
        log.info("obj:{},objectValue:{}", obj, objectValue);
        return "这是学生信息";
    }
}
