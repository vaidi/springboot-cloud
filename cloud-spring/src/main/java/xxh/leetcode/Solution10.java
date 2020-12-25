package xxh.leetcode;

/**
 * @Author: elyuan
 * @Date: 2020/12/10 5:22 下午
 */
public class Solution10 {


    public static void main(String[] args) {
        int[] array = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(array));


    }


    public static int majorityElement(int[] nums){
        return majorityElementRec(nums,0,nums.length-1);
    }


    private static int majorityElementRec(int[] nums,int lo,int hi){
        if(lo == hi){
            return nums[lo];
        }
        int middle =(hi-lo)/2+lo;
        int left =majorityElementRec(nums,lo,middle);
        int right = majorityElementRec(nums,middle+1,hi);
        if(left == right){
            return left;
        }
        int leftCount = countInRange(nums,left,lo,hi);
        int rightCount = countInRange(nums,right,lo,hi);
        return leftCount>rightCount?left:right;
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count =0;
        for(int i =lo;i<=hi;i++){
            if(nums[i] == num){
                count++;
            }
        }
        return count;
    }


}
