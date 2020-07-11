package xxh.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xxh.config.DirectMqConfig;

/**
 * @Author: xxh——el
 * @Date: 2020/7/6 6:23 下午
 */
@Component
public class DirectMqReceiver {


    @RabbitListener(queues = DirectMqConfig.QUEUE_NAME)
    @RabbitHandler
    public void processMessage(String msg) {
        System.out.println("当前线程:"+Thread.currentThread().getName()+",队列:" + DirectMqConfig.QUEUE_NAME+",接受到的消息："+ msg);
    }

}
