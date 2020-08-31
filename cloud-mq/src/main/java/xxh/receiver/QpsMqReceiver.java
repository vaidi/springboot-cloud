package xxh.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static xxh.config.QpsMqConfig.QUEUE_NAME;

/**
 * @Author: elyuan
 * @Date: 2020/8/28 4:15 下午
 */
@Component
public class QpsMqReceiver {

    @RabbitListener(queues = QUEUE_NAME)
    @RabbitHandler
    @Async("executor")
    public void receiver(Message message, Channel channel) throws IOException, InterruptedException {
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
       // sleep(1000);
        System.out.println("接收到的消息："+new String(message.getBody()));
        System.out.println("当前线程:"+Thread.currentThread().getName());
        //channel.basicNack(deliveryTag, false, false);
        channel.basicAck(deliveryTag, false);
    }


}
