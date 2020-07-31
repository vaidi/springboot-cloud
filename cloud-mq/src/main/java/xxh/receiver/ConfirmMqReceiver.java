package xxh.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import xxh.config.ConfirmMqConfig;
import xxh.config.IdempotentMqConfig;

import java.io.IOException;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2020/7/29 6:18 下午
 */
@Component
public class ConfirmMqReceiver {
    @RabbitListener(queues = ConfirmMqConfig.QUEUE_NAME)
    @RabbitHandler
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException, InterruptedException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.err.println("ConfirmMqReceiver队列接受到消息"+new String(message.getBody(), "UTF-8"));
        Integer num = Integer.valueOf(new String(message.getBody()));
        sleep(1000);
        //channel.basicAck(deliveryTag,true);
       if(num == 0){
            channel.basicAck(deliveryTag, false);
        }
        if(num ==1){
            channel.basicNack(deliveryTag,false,true);
        }
        if(num ==2){
            channel.basicNack(deliveryTag,false,false);
        }


        if(num ==3){
            channel.basicReject(deliveryTag,true);
        }
        if(num ==4){
            channel.basicReject(deliveryTag,false);
        }
        System.out.println("消息确认完毕:"+num);
        if(num ==5){
            throw new IOException();
        }

    }
}
