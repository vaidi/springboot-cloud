package xxh.main;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * @Author: elyuan
 * @Date: 2020/9/1 11:39 上午
 */
@Data
@Accessors(chain = true)
public class Container {
    /**
     * 容器的位置，可以为  null 或者空字符串
     */
    private String location;
    /**
     * 容器中的所有数值，可以为 null 或者空
     */
    private Collection<Integer> integers;
}
