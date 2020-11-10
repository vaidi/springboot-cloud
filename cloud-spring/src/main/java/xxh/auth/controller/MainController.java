package xxh.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import xxh.auth.service.UserDetailServiceImpl;

/**
 * @Author: elyuan
 * @Date: 2020/11/6 2:56 下午
 */
@Controller
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);
    @GetMapping("/main")
    public ModelAndView toMainPage() {
        //获取登录的用户名
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if(principal instanceof UserDetails) {
            username=((UserDetails)principal).getUsername();
        }else {
            username=principal.toString();
        }
        log.info("toMainPage:登陆用户userName:{}",username);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        mav.addObject("username", username);
        return mav;
    }
}
