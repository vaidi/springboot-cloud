package xxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: xxh——el
 * @Date: 2020/6/28 4:45 下午
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }

}
