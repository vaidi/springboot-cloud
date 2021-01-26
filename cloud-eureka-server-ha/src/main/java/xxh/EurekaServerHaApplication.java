package xxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: xxh——el
 * @Date: 2020/6/9 5:37 下午
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerHaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerHaApplication.class, args);
    }

}
