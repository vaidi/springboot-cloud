package xxh.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/12/10 3:04 下午
 */
public class Solution9 {
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    public static void main(String[] args) {

        int[] array ={3};

        majorityElement(array);
    }


}
