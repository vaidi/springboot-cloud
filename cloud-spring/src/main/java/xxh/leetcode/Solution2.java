package xxh.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/12/2 6:16 下午
 */
public class Solution2 {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> hashMap = new HashMap<>();
        if(nums == null){
            return null;
        }
        for(int i = 0;i <= nums.length-1;i++){
            int num = nums[i];
            if(hashMap.containsKey(num)){
                result[0] = i;
                result[1] = hashMap.get(num);
                return result;
            }
            hashMap.put(target-num,i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7, 11, 15};
        Arrays.stream(twoSum(nums,26)).forEach(System.out::println);
    }

}
