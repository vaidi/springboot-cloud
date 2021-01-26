package efficientjava;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: elyuan
 * @Date: 2021/1/16 4:03 下午
 */
public class SoftReferenceHouse {

    public static void main(String[] args) {

        List<SoftReference> list = new ArrayList<>();
        int i =0;
        while (true){
            SoftReference<House> buy = new SoftReference<>(new House());
            list.add(buy);
            System.out.println("i="+(++i));
        }
    }

}

class House{
    private static final Integer DOOR_NUMBER = 200;
    public Door[] doors = new Door[DOOR_NUMBER];
    class Door{}

}
