package xxh.receiver;

import com.rabbitmq.client.Channel;
import org.apache.http.client.utils.HttpClientUtils;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import xxh.config.IdempotentMqConfig;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2020/7/28 7:22 下午
 */
@Component
public class IdempotentMqReceiver {

    private Map<String,Object> param = new HashMap<>();


    // @RabbitListener(queues = IdempotentMqConfig.QUEUE_NAME)
    public void process(Message message) throws Exception {
        // 获取消息Id
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");
        //② 判断唯一Id是否被消费，消息消费成功后将id和状态保存在日志表中，我们从（①步骤）表中获取并判断messageId的状态即可
        //从redis中获取messageId的value
        String value = param.get(messageId)+"";
        if(value.equals("1") ){ //表示已经消费
            return; //结束
        }
        System.out.println("邮件消费者获取生产者消息" + "messageId:" + messageId + ",消息内容:" + msg);
        // 获取email参数
        // 请求地址
        JSONObject result = null;
        if (result == null) {
            // 因为网络原因,造成无法访问,继续重试
            throw new Exception("调用接口失败!");
        }
        System.out.println("执行结束....");
        //① 执行到这里已经消费成功，我们可以修改messageId的状态，并存入日志表(可以存到redis中，key为消息Id、value为状态)
        param.put(messageId,1);
    }
    @RabbitListener(queues = IdempotentMqConfig.QUEUE_NAME)
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        // 手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out
                .println(Thread.currentThread().getName() + "邮件消费者获取生产者消息msg:" + new String(message.getBody(), "UTF-8")
                        + ",messageId:" + message.getMessageProperties().getMessageId()+",deliveryTag:"+deliveryTag);
        //确认该消息已被消费,发删除消息给RabbitMQ，把消息队列中的消息删除
        channel.basicAck(deliveryTag, false);
        //消费消息失败，拒绝此消息，重回队列，让它可以继续发送到其他消费者
        //channel.basicReject(deliveryTag, true);
        //消费消息失败，拒绝多条消息，重回队列，让它们可以继续发送到其他消费者
       // channel.basicNack(deliveryTag, true, true);

      //  channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        sleep(1000);

    }
}
