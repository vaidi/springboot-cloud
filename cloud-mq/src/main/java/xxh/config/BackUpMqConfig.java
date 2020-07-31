package xxh.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: elyuan
 * @Date: 2020/7/30 5:50 下午
 * 备份交换机相关  投递到交换机上面，但是没有找到路由队列
 *
 *
 */
@Configuration
public class BackUpMqConfig {


    public static final String BUSINESS_NAME = "business_back_up_queue";
    public static final String BUSINESS_EXCHANGE_NAME ="business_back_up_exchange_name";
    public static final String BUSINESS_ROUTING_KEY ="business_back_up_routing_key";

    public static final String QUEUE_NAME ="back_up_name";
    public static final String EXCHANGE_NAME ="back_up_exchange";
    public static final String ROUTING_KEY ="back_up_routing";

    @Bean
    public Queue businessBackUpQueue(){
        return QueueBuilder.durable(BUSINESS_NAME).build();
    }

    @Bean
    public DirectExchange businessBackUpExchange(){
        ExchangeBuilder exchangeBuilder = ExchangeBuilder.directExchange(BUSINESS_EXCHANGE_NAME)
                .durable(true)
                .withArgument("alternate-exchange", EXCHANGE_NAME);
        return (DirectExchange)exchangeBuilder.build();
    }

    // 声明业务队列绑定关系
    @Bean
    public Binding businessBinding(){
        return BindingBuilder.bind(businessBackUpQueue()).to(businessBackUpExchange()).with(BUSINESS_ROUTING_KEY);
    }

    /*******************************************下面是备份队列*****************************************************/
    /*******************************************下面是备份队列*****************************************************/
    /*******************************************下面是备份队列*****************************************************/
    @Bean
    public FanoutExchange backupExchange(){
        ExchangeBuilder exchangeBuilder = ExchangeBuilder.fanoutExchange(EXCHANGE_NAME)
                .durable(true);
        return (FanoutExchange)exchangeBuilder.build();
    }
    // 声明备份队列
    @Bean
    public Queue backupQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding backupBinding(){
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }
}
