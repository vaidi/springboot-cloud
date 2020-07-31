package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/7/28 5:02 下午
 */
@Configuration
public class CurrentLimitingMqConfig {

    public static final String QUEUE_NAME = "currentLimitingQueue";


    public static final String EXCHANGE_NAME ="currentLimitingExchange";


    public static final String ROUTING_KEY = "currentLimitingKey";


    @Bean
    public Queue currentLimitingQueue() {
        return new Queue(QUEUE_NAME, true);
    }


    @Bean
    public DirectExchange currentLimitingExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingCurrentLimitingExchange() {
        return BindingBuilder.bind(currentLimitingQueue()).to(currentLimitingExchange()).with(ROUTING_KEY);
    }

}
