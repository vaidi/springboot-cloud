package xxh.restful.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 6:10 下午
 */
@Component
public class RestTemplateCustomizerHelp {

    public void rest(RestTemplateCustomizer restTemplateCustomizer,RestTemplate restTemplate){
        restTemplateCustomizer.cutomize(restTemplate);
    };


    public static void main(String[] args) {
        RestTemplateCustomizer restTemplateCustomizer=  (RestTemplate rest)->{
            String content = rest.getForObject("https://www.baidu.com", String.class);
            System.out.println(content);
        };
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("1111");
        restTemplateCustomizer.cutomize(restTemplate);
        RestTemplateCustomizerHelp restHelp = new RestTemplateCustomizerHelp();
        //用这种方法就可以指定啦
        restHelp.rest((RestTemplate rest)->{
            String content = rest.getForObject("https://www.baidu.com", String.class);
            System.out.println(content);
        },restTemplate);


    }








}
