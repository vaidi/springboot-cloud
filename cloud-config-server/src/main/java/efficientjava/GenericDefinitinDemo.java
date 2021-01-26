package efficientjava;

/**
 * @Author: elyuan
 * @Date: 2021/1/12 6:39 下午
 */
public class GenericDefinitinDemo<T> {


    static <String, T, Alibaba> String get(String string, Alibaba alibaba) {
        return string;
    }

    public static void main(String[] args) {
        Integer first =1;
        Long second =2L;
        Integer integer = get(first, second);
        Long aa = get( second);
        System.out.println(integer);



    }



    static <T> T get(T params){
        return params;
    }


}
