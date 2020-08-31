package xxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: xxh——el
 * @Date: 2020/7/4 3:01 下午
 */
@SpringBootApplication
@EnableAsync
public class MQApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQApplication.class,args);
    }

}
