package xxh.leetcode;

import java.util.Arrays;

/**
 * @Author: elyuan
 * @Date: 2020/12/10 2:12 下午
 */
public class Solution8 {

    public static int  singleNumber(int[] nums) {
        int res =0;
        for (int num : nums) {
            res = res^num;
        }
        return res;

    }


    public static void main(String[] args) {

        int[] array ={4,1,2,1,2};
        System.out.println(singleNumber(array));

    }


}
