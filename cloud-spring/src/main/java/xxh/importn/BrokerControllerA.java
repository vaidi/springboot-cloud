package xxh.importn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: elyuan
 * @Date: 2020/8/31 6:59 下午
 * 如果自定义一个注解，注解中定义@Import，则对应的该类也会到入 对应的类信息
 */
@RestController
@Import({Broker.class,MyImportDefinitionRegister.class})
public class BrokerControllerA {
    @Resource(name = "aa")
    DemoService demoService;

    @GetMapping("/get")
    public String get() {
        demoService.test();
        return "success";
    }
}
