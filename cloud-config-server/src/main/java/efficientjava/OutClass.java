package efficientjava;

/**
 * @Author: elyuan
 * @Date: 2021/1/10 5:01 下午
 */
public class OutClass {

    private class InstanceInnerClass{}

    static class StaticInnerClass{}

    public static void main(String[] args) {
        (new Thread(){}).start();
        (new Thread(){}).start();

        class MethodClass1 {}
        class MethodClass2 {}
    }


}
