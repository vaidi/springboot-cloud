package xxh.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xxh.config.TopicMqConfig;

/**
 * @Author: xxh——el
 * @Date: 2020/7/6 4:19 下午
 */
@Component
public class TopicMqReceiver {

    @RabbitListener(queues = TopicMqConfig.TOPIC_QUEUE1)
    @RabbitHandler
    public void processMessage(String msg) {
        System.out.println("当前线程："+Thread.currentThread().getName() +"，队列："+ TopicMqConfig.TOPIC_QUEUE1+"，接受消息："+ msg);
    }


    @RabbitListener(queues = TopicMqConfig.TOPIC_QUEUE2)
    @RabbitHandler
    public void processMessage1(String msg) {
        System.out.println("当前线程："+Thread.currentThread().getName() +"，队列："+TopicMqConfig.TOPIC_QUEUE1+"，接受消息："+ msg);
    }
}
