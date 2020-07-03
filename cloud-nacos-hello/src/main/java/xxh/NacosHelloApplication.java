package xxh;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: xxh——el
 * @Date: 2020/6/30 2:21 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosHelloApplication.class,args);
    }
}
