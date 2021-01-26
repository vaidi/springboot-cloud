package xxh.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import xxh.SpringStudyApplication;
import xxh.controller.HelloSwaggerController;
import xxh.es.UserXxh;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 2:44 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringStudyApplication.class)
public class MainTest {

//    @Autowired
//    private RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(HelloSwaggerController.class);





    @Test
    public void testGet(){
        UserXxh xxh = new UserXxh();

        logger.info("name:{}",xxh);

//
//        String content = this.restTemplate.getForObject("https://www.google1.cn/", String.class);
//        System.out.println(content);


    }

}
