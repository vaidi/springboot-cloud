package xxh.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: elyuan
 * @Date: 2020/7/14 7:21 下午
 */
@Component
public class ChannelBasicMqReceiver {

    public static final String BASIC_QUEUE ="basic_queue";
    public static final String BASIC_EXCHANGE ="basic_queue_exchange";



    @Bean
    public Queue fanoutQueueBasic() {
        return new Queue(BASIC_QUEUE, true);
    }
    //创建扇形交换机，参数为交换机的名称
    @Bean
    public FanoutExchange fanoutExchangeBasic() {
        return new FanoutExchange (BASIC_EXCHANGE);
    }
    //将三个队列都与该交换机绑定起来，无需binding_key
    @Bean
    public Binding bindingFanoutExchangeBasic() {
        return BindingBuilder.bind(fanoutQueueBasic()).to(fanoutExchangeBasic());
    }


    @RabbitListener(queues = BASIC_QUEUE)
    @RabbitHandler
    public void processMessage2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println(message);
        try {
            System.out.println("接受到消息，message："+message+"，channel："+channel+"，tag："+tag);
            channel.basicAck(tag,false);            // 确认消息
           // channel.basicNack(tag,false,true);//否认消息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
