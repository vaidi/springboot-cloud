package xxh.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import xxh.config.CurrentLimitingMqConfig;
import xxh.config.DirectMqConfig;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/7/28 5:11 下午
 */
@Component
@Slf4j
public class CurrentLimitingMqReceiver {

    @RabbitListener(queues = CurrentLimitingMqConfig.QUEUE_NAME)
    @RabbitHandler
    public void processMessage(@Payload String message, @Headers Map map, Channel channel) {
        long deliveryTag = (long) map.get(AmqpHeaders.DELIVERY_TAG);
        log.info("deliveryTag:{},current time:{} message:{}",deliveryTag, LocalDate.now(),message);
        try {
            //确认收到
            channel.basicAck(deliveryTag,true);
            // 表示不限制消息大小, 一次只处理一条消息, 限制只是当前消费者有效
            channel.basicQos(0, 2, false);
            Thread.sleep(10000);
            log.debug("休息三秒然后在接受消息");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
