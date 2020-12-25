package xxh.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import xxh.SpringStudyApplication;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 2:44 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringStudyApplication.class)
public class MainTest {

    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void testGet(){

        String content = this.restTemplate.getForObject("https://www.google1.cn/", String.class);
        System.out.println(content);


    }

}
