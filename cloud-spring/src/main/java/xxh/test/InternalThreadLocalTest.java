package xxh.test;

import org.apache.dubbo.common.threadlocal.InternalThread;
import org.apache.dubbo.common.threadlocal.InternalThreadLocal;

/**
 * @Author: elyuan
 * @Date: 2020/10/29 10:33 上午
 */
public class InternalThreadLocalTest {


    private static InternalThreadLocal<Integer> internalthreadlocal = new InternalThreadLocal<>();


    public static void main(String[] args) {
        new InternalThread(() -> {
            for (int i = 0; i < 5; i++) {
                internalthreadlocal.set(i);
                Integer value = internalthreadlocal.get();
                System.out.println(Thread.currentThread().getName()+":"+value);
            }
        }, "internalThread_have_set").start();

        new InternalThread(() -> {
            for (int i = 0; i < 5; i++) {
                Integer value = internalthreadlocal.get();
                System.out.println(Thread.currentThread().getName()+":"+value);
            }
        }, "internalThread_no_set").start();

    }


}
