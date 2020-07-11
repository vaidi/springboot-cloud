//package xxh.receiver;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: xxh——el
// * @Date: 2020/7/4 3:52 下午
// */
//@Component
//public class Receiver {
//    @RabbitListener(queues = "hello.queue1")
//    @RabbitHandler
//    public void processMessage1(String msg) {
//        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
//    }
//
//    @RabbitListener(queues = "hello.queue2")
//    @RabbitHandler
//    public void processMessage2(String msg) {
//        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
//    }
//}
