package xxh.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Author: elyuan
 * @Date: 2020/7/30 10:34 上午
 */
//@Component
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 默认采用下面的这种转换器
        //rabbitTemplate.setMessageConverter(new SimpleMessageConverter());
    }



    final RabbitTemplate.ConfirmCallback confirmCallback= new RabbitTemplate.ConfirmCallback() {

        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData: " + correlationData);
            System.out.println("ack: " + ack);
            if(!ack){
                System.out.println("异常处理....");
            }
        }

    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    //发送消息方法调用: 构建Message消息
//    public void send(Object message, Map<String, Object> properties) throws Exception {
//        MessageProperties mp = new MessageProperties();
//        //在生产环境中这里不用Message，而是使用 fastJson 等工具将对象转换为 json 格式发送
//        Message msg = new Message(message.toString().getBytes(),mp);
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(confirmCallback);
//        rabbitTemplate.setReturnCallback(returnCallback);
//        //id + 时间戳 全局唯一
//        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
//        rabbitTemplate.convertAndSend("BOOT-EXCHANGE-1", "boot.save", msg, correlationData);
//    }

    public void send(String exchange,String routingKey,String msg){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        log.info("开始发送消息 : " + msg.toLowerCase());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertSendAndReceive(exchange,routingKey, msg, correlationId);
        log.info("结束发送消息 : " + msg.toLowerCase());
    }

}
