package xxh.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/7/29 11:35 上午
 */
@Configuration
public class TtlMqConfig {

    public static final String QUEUE_NAME ="ttl.queue";
    public static final String EXCHANGE_NAME ="ttl.exchange";
    public final  static String ROUTING_KEY ="ttl.routing.hello";
    public final static String DELAY_QUEUE_NAME = "ttl.delay.queue";
    public final static String DELAY_EXCHANGE_NAME =  "ttl.delay.exchange";
    public final static String DELAY_ROUTING_KEY = "ttl.delay.routing";




    @Bean
    public Queue ttlQueue(){
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
       args.put("x-dead-letter-exchange", DELAY_EXCHANGE_NAME);
////       x-dead-letter-routing-key  这里声明当前队列的死信路由key
       args.put("x-dead-letter-routing-key", DELAY_ROUTING_KEY);
     //   args.put("x-message-ttl",2000);
        // 设置消息的过期时间， 单位是毫秒
//        args.put("x-message-ttl", 10000);
//        // 是否持久化
//        boolean durable = true;
//        // 仅创建者可以使用的私有队列，断开后自动删除
//        boolean exclusive = false;
//        // 当所有消费客户端连接断开后，是否自动删除队列
//        boolean autoDelete = false;
//        return new Queue(name, durable, exclusive, autoDelete, args);
        return QueueBuilder.durable(QUEUE_NAME).withArguments(args).build();
    }
    @Bean
    public DirectExchange ttlExchange(){
//        Map<String, Object> pros = new HashMap<>();
//      //  设置交换机支持延迟消息推送
//        pros.put("x-delayed-message", "topic");
     //  DirectExchange exchange = new DirectExchange(EXCHANGE_NAME, true, false, pros);
        DirectExchange exchange = new DirectExchange(EXCHANGE_NAME, true, false);
        //exchange.setDelayed(true);
        return exchange;
    }
    @Bean
    public Binding bindingTtl(){
       return BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Queue ttlDelayQueue(){
        return new Queue(DELAY_QUEUE_NAME,true);
    }


    @Bean
    public DirectExchange ttlDelayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingTtlDelay(){
        return BindingBuilder.bind(ttlDelayQueue()).to(ttlDelayExchange()).with(DELAY_ROUTING_KEY);
    }











}
