package xxh.controller;

/**
 * @Author: elyuan
 * @Date: 2020/10/29 11:01 上午
 */
public class ThreadLocalUtil {
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    public  static void setStr(String str){
        THREAD_LOCAL.set(str);
    }

    public static String getStr(){
      return   THREAD_LOCAL.get();
    }


}
