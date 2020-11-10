package xxh.auth.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: elyuan
 * @Date: 2020/11/6 2:59 下午
 */
@RestController
@RequestMapping("/test")
public class HttptestController {

    @GetMapping("/public")
    public JSONObject doPublicHandler(Long id) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "请求成功" + id);
        return json;
    }

    @PostMapping("/vip")
    public JSONObject doVipHandler(Long id) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "请求成功" + id);
        return json;
    }
}
