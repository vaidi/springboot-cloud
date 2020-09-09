package xxh.importn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: elyuan
 * @Date: 2020/8/31 7:01 下午
 */
@RestController
public class BrokerController {
    @Autowired
    Broker broker;

    @GetMapping("/get1")
    public String get() {
        broker.testBroker();
        return "success";
    }
}
