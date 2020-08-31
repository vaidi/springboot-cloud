package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/8/28 4:09 下午
 */
@Configuration
public class QpsMqConfig {

    public static final String QUEUE_NAME ="qps_queue";

    public static final String EXCHANGE_NAME ="qps_exchange";

    public static final String ROUTING_KEY="qps_routing";

    @Bean
    public Queue qpsQueue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public DirectExchange qpsExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }
    @Bean
    public Binding qpsBinding(){
        return  BindingBuilder.bind(qpsQueue()).to(qpsExchange()).with(ROUTING_KEY);
    }








}
