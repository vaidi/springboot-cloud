package xxh.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import xxh.config.BackUpMqConfig;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/7/30 6:05 下午
 */
@Component
public class BackUpMqReceiver {

    @RabbitListener(queues = BackUpMqConfig.BUSINESS_NAME)
    @RabbitHandler
    public void businessProcessMessage(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        long deliveryTag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("业务队列接受到消息："+new String(message.getBody())+",deliveryTag:"+deliveryTag);
        //确认收到
        channel.basicAck(deliveryTag, false);
    }
    @RabbitListener(queues = BackUpMqConfig.QUEUE_NAME)
    @RabbitHandler
    public void backUpProcessMessage(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        long deliveryTag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("备份队列接受到消息："+new String(message.getBody())+",deliveryTag:"+deliveryTag);
        //确认收到
        channel.basicAck(deliveryTag, false);
    }
}
