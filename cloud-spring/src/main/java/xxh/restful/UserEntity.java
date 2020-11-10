package xxh.restful;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: elyuan
 * @Date: 2020/11/9 2:37 下午
 */
@Data
@Builder
public class UserEntity {
    private String name;
    private String mobile;
}
