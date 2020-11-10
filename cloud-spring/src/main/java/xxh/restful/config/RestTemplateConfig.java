package xxh.restful.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 4:16 下午
 * https://my.oschina.net/lifany/blog/688889
 * 关于restTemplate的相关解析
 *
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        //实现超时设置，内部实际实现为 HttpClient
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //设置超时, 單位：ms
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        RestTemplate restTemplate = new RestTemplate(factory);
        //        List<HttpMessageConverter<?>> converters = Lists.newArrayList();
        //        converters.add(new FormHttpMessageConverter());
        //        converters.add(new MappingJackson2HttpMessageConverter());
        //        converters.add(new ByteArrayHttpMessageConverter());
        //        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        //        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }



    @Bean("restTemplate2")
    public RestTemplate restTemplate2() {
        restTemplateBuilder.messageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplateBuilder.interceptors(new BasicAuthorizationInterceptor("username","password"));
        //restTemplateBuilder.basicAuthorization("username","password"); 这是上面的简化模式
        //建立链接时间 与获取相映时间
        restTemplateBuilder.setConnectTimeout(10*1000).setReadTimeout(20*1000);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10*1000).setConnectionRequestTimeout(30*1000).build();
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(20).setMaxConnTotal(50).setDefaultRequestConfig(requestConfig).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplateBuilder.requestFactory(() -> requestFactory);
        RestTemplate restTemplate= restTemplateBuilder.build();
        return restTemplate;
    }


}
