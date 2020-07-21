package xxh.config;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: xxh——el
 * @Date: 2020/7/4 3:05 下午
 */
@Configuration
public class MqConfig {


    @Autowired
    private CachingConnectionFactory connectionFactory;



//    @Bean(name = "simpleMessageContainer")
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("consumer_queue");              // 监听的队列
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);        // 手动确认
//        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {      //消息处理
//            System.out.println("====接收到消息=====");
//            System.out.println(new String(message.getBody()));
//            if(message.getMessageProperties().getHeaders().get("error") == null){
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//                System.out.println("消息已经确认");
//            }else {
//                //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
//                System.out.println("消息拒绝");
//            }
//
//        });
//        return container;
//    }


    @Bean(name = "simpleContainer")
    public SimpleMessageListenerContainer simpleContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
       // container.setQueueNames("consumer_queue");                 // 监听的队列
        container.setAcknowledgeMode(AcknowledgeMode.NONE);     // NONE 代表自动确认
        container.setMessageListener(message -> {         //消息监听处理
            System.out.println("====接收到消息=====");
            System.out.println(new String(message.getBody()));
            //相当于自己的一些消费逻辑抛错误
            throw new NullPointerException("consumer fail");
        });
        return container;
    }

    @Bean(name = "consumerlistenerContainer")
    public SimpleRabbitListenerContainerFactory consumerlistenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(50);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);             //开启手动 ack
        return factory;
    }


//    @Bean
//    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
//        rabbitTemplate.setMandatory(true);
//
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
//                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
//                System.out.println("ConfirmCallback:     "+"原因："+cause);
//            }
//        });
//
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                System.out.println("ReturnCallback:     "+"消息："+message);
//                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
//                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
//                System.out.println("ReturnCallback:     "+"交换机："+exchange);
//                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
//            }
//        });
//
//        return rabbitTemplate;
//    }
}
