package efficientjava;

import efficientjava.easy.EasyAtomicReference;
import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * @Author: elyuan
 * @Date: 2021/1/14 8:47 下午
 */
public class ListMain {


    public static void main(String[] args) {
        Class<String[]> aClass = String[].class;
        Integer[] srcArray = {1,2,3,3,4,5};
        String[] strings = Arrays.copyOf(srcArray, srcArray.length, aClass);
        Arrays.stream(strings).forEach(System.out::println);
    }

}
