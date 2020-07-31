package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/7/28 6:05 下午
 */
@Configuration
public class IdempotentMqConfig {


    public static final String QUEUE_NAME ="idempotent_queue";
    public static final String EXCHANGE_NAME ="idempotent_exchange";

    @Bean
    public Queue idempotentQueue() {
        return new Queue(QUEUE_NAME, true);
    }
    //创建扇形交换机，参数为交换机的名称
    @Bean
    public FanoutExchange idempotentExchange() {
        return new FanoutExchange (EXCHANGE_NAME);
    }
    //将三个队列都与该交换机绑定起来，无需binding_key
    @Bean
    public Binding bindingFanoutExchange1() {
        return BindingBuilder.bind(idempotentQueue()).to(idempotentExchange());
    }


}
