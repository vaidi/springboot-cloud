package xxh.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xxh.restful.config.RestTemplateCustomizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 2:35 下午
 */
@RestController
@RequestMapping("/rest")
public class UserController {

    @RequestMapping(value = "getAll")
    public List<UserEntity> getUser() {
        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity = UserEntity.builder().mobile("17610721151").name("erlyuan").build();
        list.add(userEntity);
        return list;
    }

    @RequestMapping("get/{id}")
    public UserEntity getById(@PathVariable(name = "id") String id) {
        return UserEntity.builder().mobile("17610721151").name("erlyuan").build();
    }


    @RequestMapping(value = "save")
    public String save(UserEntity userEntity) {
        return "保存成功";
    }


    @RequestMapping(value = "saveByType/{type}")
    public String saveByType(UserEntity userEntity,@PathVariable("type")String type) {
        return "保存成功,type="+type;
    }
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getForEntity")
    public List<UserEntity> getAll2() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8081/rest/getAll", List.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();
        List<UserEntity> list = responseEntity.getBody();
        System.out.println(list.toString());
        return list;
    }

    //有参数的 get 请求,使用map封装参数
    @RequestMapping("getForEntity/{id}")
    public UserEntity getById4(@PathVariable(name = "id") String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        ResponseEntity<UserEntity> responseEntity = restTemplate.getForEntity("http://localhost:8081/rest/get/{id}", UserEntity.class, map);
        UserEntity userEntity = responseEntity.getBody();

        return userEntity;
    }

    public static void main(String[] args) {
        RestTemplateCustomizer restTemplateCustomizer=  (RestTemplate rest)->{
            String content = rest.getForObject("https://www.baidu.com", String.class);
            System.out.println(content);
        };
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("1111");
        restTemplateCustomizer.cutomize(restTemplate);
    }



}
