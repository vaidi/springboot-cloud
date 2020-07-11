package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机模式
 * @Author: xxh——el
 * @Date: 2020/7/6 4:02 下午
 */
@Configuration
public class TopicMqConfig {

    public final static String TOPIC_QUEUE1 = "test.topic.queue1";

    public final static String TOPIC_QUEUE2 = "test.topic.queue2";

    public final static String TOPIC_EXCHANGE = "test.topic_exchange";

    public final static String ROUTING_KEY ="test.topic.routing_key";
    public final static String ROUTING_KEY_COMMON ="test.topic.#";


    @Bean
    public Queue queueMessage1(){
        return new Queue(TopicMqConfig.TOPIC_QUEUE1);
    }

    @Bean
    public Queue queueMessage2(){
        return new Queue(TopicMqConfig.TOPIC_QUEUE2);
    }


    @Bean
    TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeMessage(){
        return BindingBuilder.bind(queueMessage1()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeMessage1(){
        return BindingBuilder.bind(queueMessage2()).to(exchange()).with(ROUTING_KEY_COMMON);
    }


}
