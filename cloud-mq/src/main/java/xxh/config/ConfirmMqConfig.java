package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/7/29 6:12 下午
 */
@Configuration
public class ConfirmMqConfig {

    public static final String QUEUE_NAME ="confirm_queue";
    public static final String EXCHANGE_NAME ="confirm_exchange";
    public static final String ROUTING_KEY= "confirm_routing_key";

    @Bean
    public Queue confirmQueue(){
        return new Queue(QUEUE_NAME,true);
    }
    @Bean
    public DirectExchange confirmExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }
    @Bean
    public Binding confirmBinding(){
       return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with(ROUTING_KEY);
    }
}
