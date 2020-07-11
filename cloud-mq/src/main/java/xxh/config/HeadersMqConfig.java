package xxh.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 主题交换机模式
 * @Author: xxh——el
 * @Date: 2020/7/6 4:02 下午
 */
@Configuration
public class HeadersMqConfig {

    public final static String HEADERS_QUEUE1 = "test.headers.queue1";

    public final static String HEADERS_QUEUE2 = "test.headers.queue2";

    public final static String HEADERS_EXCHANGE = "test.headers_exchange";


    @Bean
    public Queue headQueue1(){
        return new Queue(HeadersMqConfig.HEADERS_QUEUE1);
    }

    @Bean
    public Queue headQueue2(){
        return new Queue(HeadersMqConfig.HEADERS_QUEUE2);
    }

    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    //将headersQueue与HeadersExchange交换机绑定
    @Bean
    public Binding bingHeadersQueue() {
        //map为绑定的规则
        Map<String, Object> map = new HashMap<>();
        map.put("xxh", "erlong");
        map.put("xinhua","erlong");
        //whereAll表示需要满足所有条件
        return BindingBuilder.bind(headQueue1()).to(headersExchange()).whereAll(map).match();
    }
    @Bean
    public Binding bingHeadersQueue2() {
        //map为绑定的规则
        Map<String, Object> map = new HashMap<>();
        map.put("xxh", "erlong");
        //whereAll表示需要满足所有条件
        return BindingBuilder.bind(headQueue2()).to(headersExchange()).whereAny(map).match();
    }

}
