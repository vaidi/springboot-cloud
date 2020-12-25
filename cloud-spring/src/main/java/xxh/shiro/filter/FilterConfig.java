package xxh.shiro.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/11/27 5:29 下午
 */
@Configuration
public class FilterConfig {

    /**
     * z自定义过滤器
     * @return
     */
    @Bean
    public LogFilter logFilter() {
        return new LogFilter();
    }
    /**
     * 注册过滤器
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(logFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
