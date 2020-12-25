package xxh.leetcode;

/**
 * @Author: elyuan
 * @Date: 2020/12/8 6:13 下午
 */
public class Solution5 {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed == null || flowerbed.length ==0 || n == 0){
            return true;
        }
        for(int i =0;i<= flowerbed.length-1;i++){
            int preNum = i==0?0:flowerbed[i-1];
            int curNum = flowerbed[i];
            int nextNum =i+1<=flowerbed.length-1?flowerbed[i+1]:0;
            if(preNum== 0 && curNum == 0 && nextNum ==0){
                flowerbed[i] =1;
                n --;
            }
            if(n == 0){
                return true;
            }
        }
        return n>0?false:true;
    }

    public static void main(String[] args) {
        int[] array ={1,0,0,1,0,1,1,0,1,1};
        int n =1;
        System.out.println(canPlaceFlowers(array,1));


    }


}
