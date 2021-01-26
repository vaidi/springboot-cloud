package efficientjava;

import java.lang.reflect.Field;

/**
 * @Author: elyuan
 * @Date: 2021/1/19 4:03 下午
 */
public class ThreadLocalDemo {


    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        int j = 0;
        System.out.println(++j);
        System.out.println(j);

        int hashCode =0;
        double len = Math.pow(2,3);
        System.out.println(len);
        for(int i = 0;i<len;i++){
            hashCode = i * HASH_INCREMENT +HASH_INCREMENT;
            int bucket = hashCode & (int)(len-1);
            System.out.println("i:"+i+",bucket:"+bucket);
        }
        Thread t = new Thread(()->test("abc",false),"abc");
        t.start();
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test("def", true),"def");
        t2.start();
        t2.join();
    }

    private static void test(String s,boolean isGC)  {
        try {
            new ThreadLocal<>().set(s);
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
