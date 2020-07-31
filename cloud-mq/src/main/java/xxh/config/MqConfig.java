package xxh.config;


import org.springframework.context.annotation.Configuration;


/**
 * @Author: xxh——el
 * @Date: 2020/7/4 3:05 下午
 */
//todo 这里注释为了验证sender
@Configuration
public class MqConfig {



//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//
//
//
//
//    /**
//     * 每一条发到rabbitmq server的消息都会调一次confirm方法。
//     * 如果消息成功到达exchange，则ack参数为true，反之为false；
//     * cause参数是错误信息；
//     * CorrelationData可以理解为context，在发送消息时传入的这个参数，此时会拿到
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean(value = RabbitTemplate.ConfirmCallback.class)
//    public RabbitTemplate.ConfirmCallback confirmCallback() {
//        return new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("confirmCallback.correlationData:" + correlationData + ",ack:" + ack + ",cause:" + cause);
//
//                // do something ...
//            }
//
//        };
//    }
//
//    /**
//     * 成功到达exchange，但routing不到任何queue时会调用。
//     * 可以看到这里能直接拿到message，exchange，routingKey信息
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean(value = RabbitTemplate.ReturnCallback.class)
//    public RabbitTemplate.ReturnCallback returnCallback() {
//        return new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                // do something ...
//                System.out.println("returnCallback.message:" + message + ",replyCode:" + replyCode + ",replyText:" + replyText +
//                        "，exchange:" + exchange + "," + routingKey);
//            }
//        };
//    }

//
//    @Bean
//    public RabbitTemplate rabbitTemplate() throws Exception {
//        //根据配置决定使用哪种 RabbitTemplate
//        RabbitTemplate template = true ?
//                new CorrelationRabbitTemplate(connectionFactory) :
//                new RabbitTemplate(connectionFactory);
//        //省略其它属性设置...
//      //  RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        //如果启用 Confirm 机制，设置 ConfirmCallback
//        template.setConfirmCallback(confirmCallback());
//        //如果启用 Return 机制，设置 ReturnCallback，及打开 Mandatory
//        template.setReturnCallback(returnCallback());
//        template.setMandatory(true);
//        return template;
//    }

    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/
    /****************************************这里是分割线***************************************************/

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        //省略其它属性设置...
//
//        //根据配置决定是否开启 Confirm 机制
//        connectionFactory.setPublisherConfirms(true);
//        //根据配置决定是否开启 Return 机制
//        connectionFactory.setPublisherReturns(true);
//        return connectionFactory;
//    }
//


//    @Autowired
//    private CachingConnectionFactory connectionFactory;
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

//
//    @Bean(name = "simpleContainer")
//    public SimpleMessageListenerContainer simpleContainer(ConnectionFactory connectionFactory){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//       // container.setQueueNames("consumer_queue");                 // 监听的队列
//        container.setAcknowledgeMode(AcknowledgeMode.NONE);     // NONE 代表自动确认
//        container.setMessageListener(message -> {         //消息监听处理
//            System.out.println("====接收到消息=====");
//            System.out.println(new String(message.getBody()));
//            //相当于自己的一些消费逻辑抛错误
//            throw new NullPointerException("consumer fail");
//        });
//        return container;
//    }

//    @Bean(name = "consumerlistenerContainer")
//    public SimpleRabbitListenerContainerFactory consumerlistenerContainer(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPrefetchCount(50);
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);             //开启手动 ack
//        return factory;
//    }


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

//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        //设置并发
//        factory.setConcurrentConsumers(1);
//        SimpleMessageListenerContainer s=new SimpleMessageListenerContainer();
//        //最大并发
//        factory.setMaxConcurrentConsumers(1);
//        //消息接收——手动确认
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        //设置超时
//        factory.setReceiveTimeout(2000L);
//        //设置重试间隔
//        factory.setFailedDeclarationRetryInterval(3000L);
//        //监听自定义格式转换
//        //factory.setMessageConverter(jsonMessageConverter);
//        return factory;
//    }
//
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        //设置忽略声明异常
//        rabbitAdmin.setIgnoreDeclarationExceptions(true);
//        return rabbitAdmin;
//    }


}
