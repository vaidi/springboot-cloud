package xxh.caffeinecache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Author: elyuan
 * @Date: 2020/9/22 2:13 下午
 */
public class MaulCaCheUtil {


    public static void main(String[] args) {
        System.out.println(manulOperator("hello"));


    }


    /**
     * 手动加载
     * @param key
     * @return
     */
    public static Object manulOperator(String key) {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(100, TimeUnit.SECONDS)
                .expireAfterAccess(100, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();
        //如果一个key不存在，那么会进入指定的函数生成value
        Object value = cache.get(key, t -> setValue(key).apply(key));
        cache.put("hello",value);
//        //判断是否存在如果不存返回null
//        Object ifPresent = cache.getIfPresent(key);
//        //移除一个key
//        cache.invalidate(key);
        return value;
    }

    public static Function<String, Object> setValue(String key){
        return t -> key +System.currentTimeMillis();
    }


}
