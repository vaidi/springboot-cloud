package xxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: xxh——el
 * @Date: 2020/6/9 6:37 下午
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SingleCustomerApplication {

    /**
     * 注入 RestTemplate
     * 并用 @LoadBalanced 注解，用负载均衡策略请求服务提供者
     * 这是 Spring Ribbon 的提供的能力
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(SingleCustomerApplication.class, args);
    }
}
