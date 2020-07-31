package xxh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/7/30 6:25 下午
 */
@Configuration
public class SerializationMqConfig {

    public static final String QUEUE_NAME ="serialization_queue";
    public static final String EXCHANGE_NAME ="serialization_exchange";


    @Bean
    public Queue serializationQueue(){
        return new Queue(QUEUE_NAME,true);
    }
    @Bean
    public FanoutExchange serializationExchange(){
        return new FanoutExchange(EXCHANGE_NAME);
    }
    @Bean
    public Binding serializationBinding(){
        return BindingBuilder.bind(serializationQueue()).to(serializationExchange());
    }


    /**
     * 添加到消费段 mq能传递对象
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
    }

//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }

}
