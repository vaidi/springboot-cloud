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

/**
 * @Author: elyuan
 * @Date: 2020/7/29 6:18 下午
 */
@Component
public class ConfirmMqReceiver {

   // @RabbitHandler
    @RabbitListener(queues = ConfirmMqConfig.QUEUE_NAME)
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.err.println("ConfirmMqReceiver队列接受到消息"+new String(message.getBody(), "UTF-8"));
        // 手动签收
        channel.basicAck(deliveryTag, false);
    }
}
