package xxh.controller.service;

import org.springframework.stereotype.Service;
import xxh.controller.ThreadLocalUtil;

/**
 * @Author: elyuan
 * @Date: 2020/10/29 2:42 下午
 */
@Service
public class ThreadLocalServiceImpl {


    public void testTreadLocal() {
        String uuid = ThreadLocalUtil.getStr();
        System.out.println("ThreadLocalServiceImpl:" + uuid);
        new Thread(()->{
            System.out.println("thread:" + ThreadLocalUtil.getStr());
        }).start();
        String uuid1 = ThreadLocalUtil.getStr();
        System.out.println("ThreadLocalServiceImpl1:" + uuid1);

    }

}
