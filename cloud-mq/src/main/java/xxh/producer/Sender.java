package xxh.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Author: xxh——el
 * @Date: 2020/7/4 3:08 下午
 */
@Configuration
@Slf4j
public class Sender {
//    implements
//} RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private  RabbitTemplate rabbitTemplate;


    public Sender(RabbitTemplate template){
        this.rabbitTemplate = template;
    }

//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.err.println("Sender.confirm消息唯一标识:" + correlationData + ",确认结果ack:" + ack + ",cause失败原因:" + cause);
//    }
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.err.println("Sender.returnedMessage:" + message + ",replyCode:" + replyCode + ",replyText:" + replyText +
//                "，exchange:" + exchange + "," + routingKey);
//    }
    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String exchange,String routingKey,String msg){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        log.info("开始发送消息 : " + msg.toLowerCase());
        rabbitTemplate.convertSendAndReceive(exchange,routingKey, msg,correlationId);
        log.info("结束发送消息 : " + msg.toLowerCase());
    }

    public void send(String exchange,String routingKey,Message msg){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        log.info("开始发送消息，msg:{} ,correlationId:{}" ,msg,correlationId);
        rabbitTemplate.convertSendAndReceive(exchange,routingKey, msg, correlationId);
        log.info("结束发送消息，msg:{} " , msg);
    }


}
