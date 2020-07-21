import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xxh.MQApplication;
import xxh.config.*;
import xxh.producer.MqConfirmCallBack;
import xxh.receiver.ChannelBasicReceiver;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @Author: xxh——el
 * @Date: 2020/7/4 4:13 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MQApplication.class)
public class RabbitTests {
//    @Autowired
//    private Sender sender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MqConfirmCallBack mqConfirmCallBack;


    @Test
    public void testDeadQueue ()throws InterruptedException{
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            new Thread(()->{
                int mm = integer.getAndIncrement();
                rabbitTemplate.convertAndSend(DeadMqConfig.BUSINESS_EXCHANGE_NAME,"xx","发送的消息:"+mm);

            }).start();

        }
    }







    @Test
    public void testBasic()throws InterruptedException{
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(ChannelBasicReceiver.BASIC_EXCHANGE,"xx",mm);
            sleep(3000);
        }
    }





    @Test
    public void testConfirm()throws InterruptedException{
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(TopicMqConfig.TOPIC_EXCHANGE,"xx",mm);
            sleep(3000);
        }
    }




    @Test
    public void testHeadersQueue()throws InterruptedException{
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
            int mm = integer.getAndIncrement();
            String result ="xxh："+mm;
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("xxh", "erlong");
            //messageProperties.setHeader("xinhua", "erlong");
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(result.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(HeadersMqConfig.HEADERS_EXCHANGE,
                    "",msg);
            System.out.println("mm:"+mm);
            sleep(5000);
        }
    }



    @Test
    public void testTopicQueue()throws InterruptedException{
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(TopicMqConfig.TOPIC_EXCHANGE,
                    "test.topic.12212","xxh："+mm);
            System.out.println("mm:"+mm);
            sleep(5000);
        }
    }



    @Test
    public void testFanoutQueue()throws InterruptedException{
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(FanoutMqConfig.FANOUT_EXCHANGE,
                    "xxx","xxh："+mm);
            System.out.println("mm:"+mm);
            sleep(5000);
        }
    }






    @Test
    public void testDirectQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
             int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(DirectMqConfig.EXCHANGE_NAME,
                   "test.direct.xxh","xxh："+mm);
            System.out.println("mm:"+mm);
            sleep(5000);
        }
    }



//    @Test
//    public void testSendTopic() throws InterruptedException {
//        AtomicInteger integer = new AtomicInteger(0);
//        while (true){
//            rabbitTemplate.convertAndSend("exchange", TopicRabbitConfig.message,"xxh："+integer.getAndIncrement());
//            sleep(1000);
//        }
//    }








//    @Test
//    public void sendTest() throws Exception {
//        while(true){
//            String msg = new Date().toString()+"我是谁";
//            sender.send(msg);
//            Thread.sleep(1000);
//        }
//    }
}
