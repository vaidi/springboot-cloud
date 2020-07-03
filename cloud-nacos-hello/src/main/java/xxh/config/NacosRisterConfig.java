package xxh.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: xxh——el
 * @Date: 2020/6/30 4:25 下午
 */
@Component
public class NacosRisterConfig implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
    }
}
