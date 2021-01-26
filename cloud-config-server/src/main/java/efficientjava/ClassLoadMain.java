package efficientjava;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * @Author: elyuan
 * @Date: 2021/1/14 2:33 下午
 * 自定意类加载器
 */
public class ClassLoadMain extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = getClassFromCustomPath(name);
            if (bytes == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, bytes, 0, bytes.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        //从 r1 定义踪往中如我指定类
        return null;
    }

    public static void main(String[] args) {
        List list = new ArrayList();

        TreeMap map = new TreeMap<>();
        map.put("3",2);
        map.put("1",2);
        System.out.println( map.firstKey());



        ClassLoader classLoader = ClassLoadMain.class.getClassLoader();
        ClassLoader parent = classLoader.getParent();
        ClassLoader parent1 = parent.getParent();
        System.out.println("classLoader:" + classLoader + ",parent:" + parent + ",parent1:" + parent1);
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(urLs).forEach(System.out::println);
        Map<String, String> getenv = System.getenv();
        System.out.println("env:" + getenv);
        System.out.println("-------------------");
        ClassLoadMain customClassLoader = new ClassLoadMain();
        try {
            Class<?> clazz = Class.forName("Ab", true, customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}