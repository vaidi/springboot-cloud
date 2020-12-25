package xxh.leetcode;

import java.util.*;

/**
 * @Author: elyuan
 * @Date: 2020/12/8 6:41 下午
 */
public class Solution6 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        Map<Integer, List<String>> map = new HashMap<>();
        Set<Integer> skipKeySet = new HashSet<>();
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i <= s.length() - 1; i++) {
            String curKey = String.valueOf(chars[i]);
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Integer intKey = iterator.next();
                List<String> strList = map.get(intKey);
                long distinctCount = strList.stream().distinct().count();
                //具有重复的字段
                if (strList.size() > distinctCount) {
                    int swapInt = (int) distinctCount;
                    if (swapInt >= maxSize) {
                        maxSize = swapInt;
                        iterator.remove();
                    }
                    maxSize = (int) distinctCount;
                    skipKeySet.add(intKey);
                }
                strList.add(curKey);
            }
            List<String> curList = new ArrayList<>();
            curList.add(curKey);
            map.put(i, curList);
        }
        // int size = map.values().stream().map(e -> new HashSet(e)).mapToInt(e -> e.size()).max().getAsInt();
        return maxSize;
    }


    public static void main(String[] args) {
        String aa = "abcabcbb";
//        int num = lengthOfLongestSubstring(aa);
//        System.out.println(num);
        lengthOfLongestSubstring1(aa);

    }


    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int res = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;


    }

}
