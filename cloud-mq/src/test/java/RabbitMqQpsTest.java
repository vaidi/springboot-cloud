import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xxh.MQApplication;
import xxh.config.BackUpMqConfig;
import xxh.config.QpsMqConfig;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: elyuan
 * @Date: 2020/8/28 4:21 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MQApplication.class)
@Slf4j
public class RabbitMqQpsTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ExecutorService executorService = Executors.newFixedThreadPool(100);



    @Test
    public void testQpsMq() {
        IntStream.range(0,Integer.MAX_VALUE).forEach((int num)->{
            executorService.submit(()->{
                String message = "序号:"+num+ ",发送时间:" + LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond();
                System.out.println("message:"+message);
                //配置消息规则
                MessageProperties messageProperties = new MessageProperties();
                //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
                Message msg = new Message(message.getBytes(), messageProperties);
                rabbitTemplate.convertAndSend(QpsMqConfig.EXCHANGE_NAME, QpsMqConfig.ROUTING_KEY, msg);
            });
        });

    }
}
