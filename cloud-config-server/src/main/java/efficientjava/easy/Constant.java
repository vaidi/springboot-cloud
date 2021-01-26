package efficientjava.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2021/1/20 9:11 下午
 */
public class Constant {
    protected static final Map<Thread, EasyThreadLocal.ThreadLocalMap> map = new HashMap<>();
}
