package xxh.leetcode;

import java.util.Arrays;

/**
 * @Author: elyuan
 * @Date: 2020/12/1 2:23 下午、
 * 主要考察了二分查找法
 */
public class Solution1 {

    public static int[] searchRange(int[] nums, int target) {
        int[] resultArray = {-1, -1};
        if (nums == null || nums.length == 0) {
            return resultArray;
        }
        boolean firstOk = true;
        boolean lastOk = true;
        int lengthLess = nums.length - 1;
        int middle = lengthLess >>> 1;
        int start = 0;
        while (start < lengthLess) {
            middle = (start + lengthLess) >>> 1;
            if (nums[middle] == target) {
                resultArray[0] = middle;
                resultArray[1] = middle;
            }
            if (middle > 0 && nums[middle - 1] == target) {
                resultArray[0] = middle;
            }
            if (middle < lengthLess && nums[middle + 1] == target) {
                resultArray[1] = middle;
            }


        }


        return resultArray;
    }

    public static void main(String[] args) {
        int[] array = {5, 7, 7, 8, 8, 10, 11};
        System.out.println(findFirst(array, 5));
        System.out.println(findLast(array, 8));
        int i=Integer.MAX_VALUE+1;
        System.out.println(i);
    }

    private static int findLast(int[] array, int target) {
        if(array.length<1){
            return -1;
        }
        int length = array.length;
        int left =0;
        int right = length-1;
        while (left <= right){
            int middle =(right+left)>>>1;
            if(array[middle]== target&& (middle == length || array[middle+1]!= target)){
                return middle;
            }else if(array[middle] <= target){
                left = middle +1;
            }else{
                right = middle-1;
            }
        }
        return -1;
    }



    public static int findFirst(int[] array, int target) {
        int length = array.length;
        if (length < 1) {
            return -1;
        }
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high) >>>1;
            if (array[mid] == target && (mid - 1 < 0 || array[mid - 1] != target)) {
                return mid;
            } else if (array[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}
