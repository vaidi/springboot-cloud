import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xxh.MQApplication;
import xxh.config.*;
import xxh.data.UserData;
import xxh.producer.MQSender;
import xxh.producer.Sender;
import xxh.receiver.ChannelBasicMqReceiver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @Author: xxh——el
 * @Date: 2020/7/4 4:13 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MQApplication.class)
@Slf4j
public class RabbitTests {
    @Autowired
    private Sender sender;

//    @Autowired
//    private MQSender mqSender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Autowired
//    private MqConfirmCallBack mqConfirmCallBack;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Autowired
    private CachingConnectionFactory connectionFactory;


    @Test
    public void SerializationMq() throws InterruptedException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        while (true){
            UserData userData = UserData.builder().mobile("1761021152").name("xxh").build();
            System.out.println("userData:"+userData);
            //rabbitTemplate.convertAndSend(SerializationMqConfig.EXCHANGE_NAME,"",userData);
            rabbitTemplate.convertAndSend(SerializationMqConfig.EXCHANGE_NAME,"",userData);
            sleep(1000);
        }
    }


    @Test
    public void testBackUpMq(){
        String message = "hello world!" +",发送时间:"+ dtf.format(LocalDateTime.now());
        //配置消息规则
        MessageProperties messageProperties = new MessageProperties();
        //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
        Message msg = new Message(message.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(BackUpMqConfig.BUSINESS_EXCHANGE_NAME,BackUpMqConfig.BUSINESS_ROUTING_KEY,msg);
        rabbitTemplate.convertAndSend(BackUpMqConfig.BUSINESS_EXCHANGE_NAME,BackUpMqConfig.BUSINESS_ROUTING_KEY+"..",msg);
    }



    @Test
    public void testSender() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
            String message = "hello world!" +",发送时间:"+ dtf.format(LocalDateTime.now())+integer.getAndIncrement();
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(message.getBytes(), messageProperties);
            //rabbitTemplate.convertAndSend(ConfirmMqConfig.EXCHANGE_NAME, ConfirmMqConfig.ROUTING_KEY, msg);
            sender.send(ConfirmMqConfig.EXCHANGE_NAME, ConfirmMqConfig.ROUTING_KEY, message);
            //rabbitTemplate.convertAndSend(ConfirmMqConfig.EXCHANGE_NAME, ConfirmMqConfig.ROUTING_KEY, message);
            sleep(1000);
        }

    }







    @Test
    public void testConfirmQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
            String message = "hello world!" +",发送时间:"+ dtf.format(LocalDateTime.now())+integer.getAndIncrement();
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(message.getBytes(), messageProperties);
            //rabbitTemplate.convertAndSend(ConfirmMqConfig.EXCHANGE_NAME, ConfirmMqConfig.ROUTING_KEY, msg);
            rabbitTemplate.convertAndSend(ConfirmMqConfig.EXCHANGE_NAME, ConfirmMqConfig.ROUTING_KEY, msg);
            sleep(50);
        }

    }

    @Test
    public void testConnectionFactory() throws InterruptedException {
        Properties properties = connectionFactory.getPublisherConnectionFactoryCacheProperties();
        log.info("connectionFactory:{}",connectionFactory);
        log.info("properties:{}",properties);
        System.err.println("hello world");
        sleep(10000);
    }



    @Test
    public void testTtlQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true){
            int num = integer.getAndIncrement();
            String message = "hello world!" +",发送时间:"+ dtf.format(LocalDateTime.now())+",num:"+num;
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            if(num == 0){
               messageProperties.setExpiration("60000");

            }else{
                messageProperties.setExpiration("2000");
            }


          //  messageProperties.setDelay(6000);
            //messageProperties.setHeader("xinhua", "erlong");
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(message.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(TtlMqConfig.EXCHANGE_NAME, TtlMqConfig.ROUTING_KEY, msg);


            //rabbitTemplate.convertAndSend(TtlMqConfig.EXCHANGE_NAME, TtlMqConfig.ROUTING_KEY,message);
            //发送消息时指定 header 延迟时间
//            rabbitTemplate.convertAndSend(TtlMqConfig.EXCHANGE_NAME, TtlMqConfig.ROUTING_KEY, message,
//                    new MessagePostProcessor() {
//                        @Override
//                        public Message postProcessMessage(Message message) throws AmqpException {
//                            //设置消息持久化
//                            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//                            message.getMessageProperties().setHeader("x-delay", "6000");
//                            //message.getMessageProperties().setDelay(6000);
//                            return message;
//                        }
//                    }, correlationData);
            sleep(100);
        }
//        Map<String, Object> headers = new HashMap<String, Object>();
//        headers.put("myhead1", "111");
//        headers.put("myhead2", "222");
//
//        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
//                .deliveryMode(2)
//                .contentEncoding("UTF-8")
//                .expiration("100000")
//                .headers(headers)
//                .build();
//        MessagePostProcessor messagePostProcessor = new me
//        String msg = "test message";
//        rabbitTemplate.convertAndSend();
//        rabbitTemplate.basicPublish("", queueName, properties, msg.getBytes());

    }









    @Test
    public void testIdempotentQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(IdempotentMqConfig.EXCHANGE_NAME, "", "hello :" + mm);
            sleep(1000000);

        }
    }


    @Test
    public void testCurrentLimitingQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            new Thread(() -> {
                int mm = integer.getAndIncrement();
                rabbitTemplate.convertAndSend(CurrentLimitingMqConfig.EXCHANGE_NAME, CurrentLimitingMqConfig.ROUTING_KEY, "发送的消息:" + mm);
            }).run();
        }
    }


    @Test
    public void testDeadQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            new Thread(() -> {
                int mm = integer.getAndIncrement();
                rabbitTemplate.convertAndSend(DeadMqConfig.BUSINESS_EXCHANGE_NAME, "xx", "发送的消息:" + mm);
            }).start();
        }
    }


    @Test
    public void testBasic() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(ChannelBasicMqReceiver.BASIC_EXCHANGE, "xx", mm);
            sleep(3000);
        }
    }


    @Test
    public void testConfirm() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(TopicMqConfig.TOPIC_EXCHANGE, "xx", mm);
            sleep(3000);
        }
    }


    @Test
    public void testHeadersQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            String result = "xxh：" + mm;
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("xxh", "erlong");
            //messageProperties.setHeader("xinhua", "erlong");
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(result.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(HeadersMqConfig.HEADERS_EXCHANGE,
                    "", msg);
            sleep(5000);
        }
    }


    @Test
    public void testTopicQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(TopicMqConfig.TOPIC_EXCHANGE,
                    "test.topic.12212", "xxh：" + mm);
            System.out.println("mm:" + mm);
            sleep(5000);
        }
    }


    @Test
    public void testFanoutQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(FanoutMqConfig.FANOUT_EXCHANGE,
                    "xxx", "xxh：" + mm);
            System.out.println("mm:" + mm);
            sleep(5000);
        }
    }


    @Test
    public void testDirectQueue() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        while (true) {
            int mm = integer.getAndIncrement();
            rabbitTemplate.convertAndSend(DirectMqConfig.EXCHANGE_NAME,
                    "test.direct.xxh", "xxh：" + mm);
            System.out.println("mm:" + mm);
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
