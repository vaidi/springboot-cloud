package efficientjava;

/**
 * @Author: elyuan
 * @Date: 2021/1/12 3:15 下午
 */
public class ParamPassing {

    private static String stringStatic ="oldStr";
    private static StringBuilder stringBuilderStat
            = new StringBuilder("oldStringBuilder");

    public static void main(String[] args) {
        method(stringBuilderStat,stringBuilderStat);
        System.out.println(stringBuilderStat);
    }
    public static void method(StringBuilder stringBuilderStat1 ,
                              StringBuilder stringBuilderStatic2) {
        stringBuilderStat1.append("first");
        stringBuilderStatic2.append("second");
        stringBuilderStat1 = new StringBuilder("init");
        stringBuilderStat1.append("new method");


    }

}
