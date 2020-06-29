package xxh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Author: xxh——el
 * @Date: 2020/6/28 4:47 下午
 */
@RestController
public class ConsulController {


    @Autowired
    private DiscoveryClient discoveryClient;


    public String serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("STORES");
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().toString();
        }
        return null;
    }


    @RequestMapping("/")
    public String home() {
        List<ServiceInstance> list = discoveryClient.getInstances("STORES");
        if (list != null && list.size() > 0 ) {
            System.out.println("url:"+list.get(0).toString());
        }

        return UUID.randomUUID().toString();
    }
    @RequestMapping("/xxh")
    public String helloXxh() {
        return UUID.randomUUID().toString();
    }
}
