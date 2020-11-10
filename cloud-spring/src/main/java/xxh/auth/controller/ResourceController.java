package xxh.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: elyuan
 * @Date: 2020/11/6 2:59 下午
 */
@Controller
public class ResourceController {
    @GetMapping("/publicResource")
    public String toPublicResource() {
        return "resource/public";
    }

    @GetMapping("/vipResource")
    public String toVipResource() {
        return "resource/vip";
    }
}
