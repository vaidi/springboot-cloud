package xxh.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连模式
 * @Author: xxh——el
 * @Date: 2020/7/6 5:50 下午
 */
@Configuration
public class DirectMqConfig {


    // 声明
    public final static String EXCHANGE_NAME = "test_direct_exchange";
    public final static String QUEUE_NAME = "test_direct_queue";
    public final static String ROUTING_KEY = "test.direct";
    public final static String ROUTING_KEY_XXH = "test.direct.xxh";
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue directQueue(){
        return new Queue(QUEUE_NAME);
    }
    @Bean
    Binding bindingDirectExchange(){
       return BindingBuilder.bind(directQueue()).to(directExchange()).with(ROUTING_KEY);
    }
    @Bean
    Binding bindingDirectExchange2(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(ROUTING_KEY_XXH);
    }
}
