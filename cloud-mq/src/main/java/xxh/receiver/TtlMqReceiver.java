package xxh.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import xxh.config.TtlMqConfig;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/7/29 11:51 上午
 */
@Component
public class TtlMqReceiver {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

  //  @RabbitListener(queues = TtlMqConfig.QUEUE_NAME)
    @RabbitHandler
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("延迟的队列接受到消息"+new String(message.getBody(), "UTF-8")+",deliveryTag:"+deliveryTag);
        //throw  new IOException();
        // 手动签收
        channel.basicAck(deliveryTag, false);

    }
    @RabbitListener(queues = TtlMqConfig.DELAY_QUEUE_NAME)
    @RabbitHandler
    public void process1(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.err.println("延迟的队列的死信队列接受到消息"+new String(message.getBody(), "UTF-8")+",deliveryTag:"+deliveryTag
        +",接受时间:"+ dtf.format(LocalDateTime.now()));
        // 手动签收
        channel.basicAck(deliveryTag, false);
    }

}
