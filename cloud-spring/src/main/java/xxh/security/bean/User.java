package xxh.security.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:04 下午
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;

    private String username;

    private String password;

}
