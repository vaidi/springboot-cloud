package xxh.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xxh——el
 * @Date: 2020/6/9 6:41 下午
 */
@FeignClient("single-provider")
public interface IHelloService {
    @RequestMapping(value = "/hello")
    String hello();
    @RequestMapping(value = "nice")
    String nice();
}