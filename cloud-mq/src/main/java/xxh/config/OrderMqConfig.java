package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: elyuan
 * @Date: 2020/7/30 4:56 下午
 */
@Configuration
public class OrderMqConfig {

    public static final String QUEUE_NAME ="order_queue";
    public static final String EXCHANGE_NAME ="order_exchange";

    @Bean
    public Queue orderQueue(){
        return new Queue(QUEUE_NAME,true);
    }
    @Bean
    public FanoutExchange orderExchange(){
        return new FanoutExchange(EXCHANGE_NAME,true,false);
    }

    @Bean
    public Binding orderBinding(){
      return   BindingBuilder.bind(orderQueue()).to(orderExchange());
    }

}
