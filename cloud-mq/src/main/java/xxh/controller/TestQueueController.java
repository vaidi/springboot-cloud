package xxh.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xxh.config.ConfirmMqConfig;
import xxh.config.OrderMqConfig;
import xxh.config.TtlMqConfig;
import xxh.producer.Sender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2020/7/30 4:14 下午
 */
@RestController
@RequestMapping("/queue")
public class TestQueueController {
    private AtomicInteger integer = new AtomicInteger(0);

    private static final String OK = "OK.num:";


    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");


    @GetMapping("/confirm")
    public String testConfirm(@RequestParam(name = "num") int num) {
        String message = num + "";
        MessageProperties messageProperties = new MessageProperties();
        Message msg = new Message(message.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(ConfirmMqConfig.EXCHANGE_NAME, ConfirmMqConfig.ROUTING_KEY, msg);
        return OK  + num;
    }


    @GetMapping("/order")
    public void testOrderQueue() {
        while (true) {
            int num = integer.getAndIncrement();
            String message = "hello world!" + ",发送时间:" + dtf.format(LocalDateTime.now()) + ",num:" + num;
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(message.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(OrderMqConfig.EXCHANGE_NAME, "", msg);
            if (num == 500) {
                break;
            }
        }
    }


    /**
     * 验证延时队列
     */
    @GetMapping("/ttl")
    public void testTtl() {
        while (true) {
            int num = integer.getAndIncrement();
            String message = "hello world!" + ",发送时间:" + dtf.format(LocalDateTime.now()) + ",num:" + num;
            //配置消息规则
            MessageProperties messageProperties = new MessageProperties();
            if (num == 0) {
                messageProperties.setExpiration("2000");

            } else if (num == 1) {
                messageProperties.setExpiration("20000");
            } else if (num > 1) {
                messageProperties.setExpiration("2");
            }
            //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
            Message msg = new Message(message.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(TtlMqConfig.EXCHANGE_NAME, TtlMqConfig.ROUTING_KEY, msg);
            if (num == 5) {
                break;
            }
        }
    }

    /*************************************************分割线**************************************************************/
    /*************************************************分割线**************************************************************/
    /*************************************************分割线**************************************************************/
    /*************************************************分割线**************************************************************/
    /*************************************************分割线**************************************************************/
    /*************************************************分割线**************************************************************/
    /*************************************************分割线**************************************************************/

    @Autowired
    private Sender sender;

    @GetMapping("/sender")
    public String testSender(@RequestParam(name = "num") String num) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        String message = num;
        //配置消息规则
        MessageProperties messageProperties = new MessageProperties();
        //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
        Message msg = new Message(message.getBytes(), messageProperties);
        sender.send(ConfirmMqConfig.EXCHANGE_NAME+".", ConfirmMqConfig.ROUTING_KEY+".", msg);
        return OK+num;
    }

}
