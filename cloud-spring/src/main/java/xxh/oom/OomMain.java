package xxh.oom;

import javafx.beans.binding.When;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Author: elyuan
 * @Date: 2020/12/11 11:16 上午
 */
public class OomMain {

    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObj.class);
            enhancer.setUseCache(true);
            enhancer.setCallback((MethodInterceptor)(o,method,objects,methodProxy)->{
                System.out.println("Before invoke super method");
                methodProxy.invokeSuper(objects,args);
                System.out.println("After invoke super method");
                return null;
            });
            enhancer.create();
        }

    }
    static class OOMObj{}

}
