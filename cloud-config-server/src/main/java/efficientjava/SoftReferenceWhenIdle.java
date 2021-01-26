package efficientjava;

import javafx.beans.binding.When;

import javax.net.ssl.HostnameVerifier;
import java.lang.ref.SoftReference;

/**
 * @Author: elyuan
 * @Date: 2021/1/16 4:20 下午
 */
public class SoftReferenceWhenIdle {



    public static void main(String[] args) {

        House house = new House();
        SoftReference<House> buy = new SoftReference<>(house);
        house = null;
        while (true) {
            System.gc();
            System.runFinalization();
            if(buy.get() == null){
                System.err.println("得到的房子为null:"+buy.get());
            }else{
                System.out.println("得到了房子");
            }


        }


    }


}
