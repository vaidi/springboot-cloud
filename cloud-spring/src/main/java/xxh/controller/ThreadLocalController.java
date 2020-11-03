package xxh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxh.controller.service.ThreadLocalServiceImpl;

import java.util.UUID;

/**
 * @Author: elyuan
 * @Date: 2020/10/29 10:53 上午
 */
@RequestMapping("/thread")
@RestController
public class ThreadLocalController {


    @Autowired
    private ThreadLocalServiceImpl threadLocalService;





    @GetMapping("/local")
    public void testLocal(){
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid:"+uuid);
        ThreadLocalUtil.setStr(uuid);
        threadLocalService.testTreadLocal();
    }





}
