package xxh.data;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: elyuan
 * @Date: 2020/7/30 6:24 下午
 */
@Data
@Builder
public class UserData implements Serializable {
    public UserData() {
    }

    public UserData(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    private String name;
    private String mobile;

}
