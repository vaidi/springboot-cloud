//package xxh.producer;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
///**
// * @Author: elyuan
// * @Date: 2020/7/14 6:54 下午
// */
////@Configuration
//public class MqReturnCallback implements RabbitTemplate.ReturnCallback {
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("消息主体 message : "+message);
//        System.out.println("消息主体 message : "+replyCode);
//        System.out.println("描述："+replyText);
//        System.out.println("消息使用的交换器 exchange : "+exchange);
//        System.out.println("消息使用的路由键 routing : "+routingKey);
//    }
//}
