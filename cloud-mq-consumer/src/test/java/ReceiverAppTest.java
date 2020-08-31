//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.integration.support.MessageBuilder;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @Author: xxh——el
// * @Date: 2020/6/10 8:23 下午
// */
//@RunWith(SpringRunner.class)
//@EnableBinding(value = {ReceiverAppTest.SinkSender.class})
//public class ReceiverAppTest {
//
//    @Autowired
//    private SinkSender sinkSender;
//
//    @Test
//    public void sinkSenderTester() {
//        sinkSender.output().send(MessageBuilder.withPayload("produce a message to " + Sink.INPUT + " channel").build());
//    }
//
//    public interface SinkSender {
//        @Output(Sink.INPUT)
//        MessageChannel output();
//    }
//}
