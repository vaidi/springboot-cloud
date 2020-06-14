package xxh.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author: xxh——el
 * @Date: 2020/6/10 8:10 下午
 */

@EnableBinding(value = {Sink.class})
@Slf4j
public class SinkConsumer {

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        log.info("Received from default channel : {}", payload.toString());
    }

}
