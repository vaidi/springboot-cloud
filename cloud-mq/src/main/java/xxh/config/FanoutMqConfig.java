package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形模式  没有routing_key
 * @Author: elyuan
 * @Date: 2020/7/11 4:25 下午
 */
@Configuration
public class FanoutMqConfig {

    public static final String FANOUT_QUEUE1 = "test.fanout.queue1";
    public static final String FANOUT_QUEUE2 = "test.fanout.queue2";
    public static final String FANOUT_QUEUE3 = "test.fanout.queue3";

    public static final String FANOUT_EXCHANGE ="test.fanout_exchange";

    //创建三个队列1,2,3
    //Queue的第一个参数为队列名称，第二个参数为是否持久存在
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE1, true);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE2, true);
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue(FANOUT_QUEUE3, true);
    }
    //创建扇形交换机，参数为交换机的名称
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange (FANOUT_EXCHANGE);
    }
    //将三个队列都与该交换机绑定起来，无需binding_key
    @Bean
    public Binding bindingFanoutExchange1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutExchange2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingFanoutExchange3() {
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }

}
