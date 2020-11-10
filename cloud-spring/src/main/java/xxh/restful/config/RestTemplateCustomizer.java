package xxh.restful.config;

import org.springframework.web.client.RestTemplate;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 5:51 下午
 */
@FunctionalInterface
public interface RestTemplateCustomizer {
    void cutomize(RestTemplate restTemplate);
}
