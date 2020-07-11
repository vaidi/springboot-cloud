package xxh.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xxh.config.DirectMqConfig;
import xxh.config.FanoutMqConfig;

/**
 * @Author: xxh——el
 * @Date: 2020/7/6 6:23 下午
 */
@Component
public class FanoutMqReceiver {


    @RabbitListener(queues = FanoutMqConfig.FANOUT_QUEUE1)
    @RabbitHandler
    public void processMessageQueue1(String msg) {
        System.out.println("当前线程:"+Thread.currentThread().getName()+",队列:" + FanoutMqConfig.FANOUT_QUEUE1+",接受到的消息："+ msg);
    }


    @RabbitListener(queues = FanoutMqConfig.FANOUT_QUEUE2)
    @RabbitHandler
    public void processMessageQueue2(String msg) {
        System.out.println("当前线程:"+Thread.currentThread().getName()+",队列:" + FanoutMqConfig.FANOUT_QUEUE2+",接受到的消息："+ msg);
    }

    @RabbitListener(queues = FanoutMqConfig.FANOUT_QUEUE3)
    @RabbitHandler
    public void processMessageQueue3(String msg) {
        System.out.println("当前线程:"+Thread.currentThread().getName()+",队列:" + FanoutMqConfig.FANOUT_QUEUE3+",接受到的消息："+ msg);
    }


}
