package xxh.leetcode;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Author: elyuan
 * @Date: 2020/12/9 6:35 下午
 */
public class Solution7 {


    public static int reverse(int x) {
        if(x ==0 || x==-0){
            return x;
        }
        String mm = String.valueOf(x);
        final char symbol = '-';
        int length = mm.length();
        char[] chars = new char[length];
        char[] intCharArray = mm.toCharArray();
        boolean negativeOk = false;
        for (int i = 0; i < length; i++) {
            if (intCharArray[i] == symbol) {
                negativeOk = true;
            }
            chars[i] = intCharArray[length - 1 - i];
        }
        String str = new String(chars);
        if (str.charAt(0) == '0') {
            str = str.substring(1);
        }
        if(negativeOk){
            str = str.replace("-","");
        }
        int num;
        try {
            num= Integer.parseInt(str);
        }catch (Exception e){
            return 0;
        }
        return negativeOk ? -num: num;
    }


    public static void main(String[] args) {
        System.out.println(reverse(1534236469));

    }


}
