package xxh.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xxh.bean.UserBean;

/**
 * @Author: elyuan
 * @Date: 2021/1/23 4:27 下午
 */
@RestController
public class CommonController {

    @GetMapping("/user")
    public UserBean getUser(){
        return new UserBean("xxh","13462960397");
    }

}
