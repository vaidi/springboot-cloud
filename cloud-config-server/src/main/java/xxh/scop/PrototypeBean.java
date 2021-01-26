package xxh.scop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: elyuan
 * @Date: 2021/1/8 11:56 上午
 */
@Scope(value = "prototype")
@Component
public class PrototypeBean {

    public PrototypeBean(){
        System.err.println("我是一个多例");
    }


}
