package xxh.easyexcel;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import xxh.easyexcel.bean.SmsMessage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: elyuan
 * @Date: 2020/11/11 7:10 下午
 */
public class MainTest {

    public static void main(String[] args) {

        String mobiles = "17610721152,12";

        StringBuilder errorMobile = new StringBuilder();
        String[] attr = mobiles.split(";");
        if(attr != null && attr.length>0){
            for (String mobileNo : attr) {
                String[] mobileNoAttr =   mobileNo.split(",");
                if(mobileNoAttr != null && mobileNoAttr.length>1){
                    String mobile = mobileNoAttr[0];
                    String no = mobileNoAttr[1];
                    send(mobile,no,errorMobile,new AtomicInteger());

                }
            }
        }
        System.err.println("发送失败的号码："+errorMobile);
    }

    public static void send(String mobile, String no, StringBuilder str, AtomicInteger atomicInteger){
        mobile = mobile.replace("\n","");
        no = no.replace("\n","");
        RestTemplate restTemplate = new RestTemplate();
        SmsMessage message = new SmsMessage();
        // message.setContent("【开课吧】哈喽，同学您好~ 请在购买后48小时内添加助教，激活您的《Python零基础入门体验课程》，逾期课程失效将无法学习，现在赶快点击链接添加老师，带你上课吧~https://wx.kaikeba.com/paysuccess?orderId=${no}");
        message.setContent(no);
        message.setRecipient(mobile);
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("username", "JSM4048102");
        data.add("password", "b0njsmswex");
        data.add("veryCode", "12i64cg34zm7");
        data.add("mobile", message.getRecipient());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.getContent().length; i++) {
            sb.append("@").append(i + 1).append("@=").append(message.getContent()[i]);
            if (i != message.getContent().length - 1) {
                sb.append(",");
            }
        }
        data.add("content", sb.toString()
        );
        data.add("msgtype", "2");
        data.add("tempid","JSM40481-0079");
        data.add("code", "utf-8");
        String result = restTemplate.postForEntity("http://112.74.76.186:8030/service/httpService/httpInterface.do?method=sendUtf8Msg", data, String.class).getBody();
        if(result.indexOf("<status>0</status>")>0){
            System.out.println("发送成功"+atomicInteger.incrementAndGet());
        }else{
            str.append(mobile+",");
        }

    }

}
