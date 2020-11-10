package xxh.restful.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 6:31 下午
 */
@Component
public class RestTemplateProxy {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param url          请求地址
     *                     参数可以通过 http://localhost:8888/juheServer/juhe/info/queryCustomer.do?taxNo=92330424MA29G7GY5W
     *                     或者 http://localhost:8888/juheServer/juhe/info/queryCustomer.do+String.format("?taxNo=%s&order=%s", "92330424MA29G7GY5W","1212121212");
     * @param responseType 返回值的类型
     * @return
     * @date 2020/3/5 5:28 PM
     */
    public <T> T getForObject(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    /**
     * 通过json的方式请求服务，不需要将数据格式化，直接将请求对象传入即可
     * 可以是map，可以是一个bean
     *
     * @param url          请求接口
     * @param requestParam 请求实体
     * @param responseType 返回对象的clazz
     * @return
     * @date 2020/3/5 5:36 PM
     */
    public <T> T postForObjectJSON(String url, Object requestParam, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(requestParam, headers);
        return restTemplate.postForObject(url, httpEntity, responseType);
    }

    /**
     * 通过Form表单的方式提交
     *
     * @param url          请求接口
     * @param requestParam 请求实体，可以是一个实体，也可以一个map
     * @param responseType 返回对象的clazz
     * @return
     * @author xiagwei
     * @date 2020/3/5 5:42 PM
     */
    public <T> T postForObjectForm(String url, @NotNull Object requestParam, Class<T> responseType) {
        MultiValueMap<String, Object> valueRequestMap = creatValueMap(requestParam);
        return restTemplate.postForObject(url, valueRequestMap, responseType);
    }

    /**
     * 图片上传
     *
     * @param url          请求地址
     * @param body         请求体
     *                     MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
     *                     body.add("uploadFile", new FileSystemResource(ImageUtil.downloadImgByUrl(url)));
     * @param responseType 返回结果的clazz对象
     * @return
     * @author xiagwei
     * @date 2020/3/5 6:05 PM
     */
    public <T> T uploadImg(@NotNull String url, @NotNull MultiValueMap<String, Object> body, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url, requestEntity, responseType);
    }


    private MultiValueMap creatValueMap(Object requestParam) {
        MultiValueMap<String, Object> valueRequestMap = new LinkedMultiValueMap<>();
        Map<String, Object> param = null;
        if (requestParam instanceof Map) {
            param = (Map<String, Object>) requestParam;
        } else {
            //param = BeanUtils.beanToMap(requestParam);
            param = JSONObject.parseObject(JSON.toJSONString(requestParam));
        }
        for (String key : param.keySet()) {
            valueRequestMap.add(key, param.get(key));
        }
        return valueRequestMap;
    }


}
