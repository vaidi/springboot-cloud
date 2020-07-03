package xxh.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: xxh——el
 * @Date: 2020/6/30 2:24 下午
 */
@Component
@Data
@RefreshScope
public class NacosConfig {

    @Value("${erlong:123}")
    private String erlong;
}
