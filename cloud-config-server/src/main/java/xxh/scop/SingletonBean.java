package xxh.scop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: elyuan
 * @Date: 2021/1/8 11:42 上午
 */

@Scope(value = "singleton")
@Component
public class SingletonBean {

    public SingletonBean(){
        System.err.println("我是一个单例");
    }


}
