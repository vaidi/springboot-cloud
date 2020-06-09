package xxh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: xhh
 * @Date: 2020/6/6 7:36 下午
 */

@Component
@Data
@ConfigurationProperties(prefix = "data")
public class GitAutoRefreshConfig {
    @Data
    public static class UserInfo {
        private String username;
        private String password;
    }

    private String env;
    private UserInfo user;

}
