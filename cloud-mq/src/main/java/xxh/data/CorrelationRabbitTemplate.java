//package xxh.data;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//
///**
// * @Author: elyuan
// * @Date: 2020/7/29 6:01 下午
// */
//public class CorrelationRabbitTemplate extends RabbitTemplate {
//
//
//    public CorrelationRabbitTemplate(ConnectionFactory connectionFactory) {
//        super(connectionFactory);
//    }
//
//    @Override
//    public void send(final String exchange, final String routingKey,
//                     final Message message, final CorrelationData correlationData)
//            throws AmqpException {
//        super.send(exchange, routingKey, message, correlationData == null ? new MessageCorrelationData(message) : correlationData);
//    }
//}
