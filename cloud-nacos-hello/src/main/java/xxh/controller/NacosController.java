package xxh.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xxh.config.NacosConfig;

import java.util.List;

/**
 * @Author: xxh——el
 * @Date: 2020/6/30 2:29 下午
 */

@RestController
public class NacosController {

    @Autowired
    private NacosConfig nacosConfig;

    @NacosInjected
    private NamingService namingService;

    @GetMapping("/")
    public String helloNacos(){
        return nacosConfig.getErlong();
    }

    @GetMapping(value = "/get")
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @GetMapping("getNameService")
    public String getNameService() throws NacosException {
        List<ServiceInfo> list = namingService.getSubscribeServices();
        if(list != null && list.size() !=0){
            list.stream().forEach(System.out::println);
        }
        return "";
    }


}
