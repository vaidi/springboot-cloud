//package xxh.producer;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * @Author: elyuan
// * @Date: 2020/7/14 6:47 下午
// */
////@Configuration
//public class MqConfirmCallBack implements RabbitTemplate.ConfirmCallback {
//
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("消息唯一标识："+correlationData);
//        System.out.println("确认结果："+ack);
//        System.out.println("失败原因："+cause);
//    }
//}
