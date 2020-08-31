package xxh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: elyuan
 * @Date: 2020/8/28 5:06 下午
 */
@Configuration
public class ExecutorConfig {

    @Bean("executor")
    public ExecutorService getExecutor(){
        return   Executors.newFixedThreadPool(10);

    }
}
